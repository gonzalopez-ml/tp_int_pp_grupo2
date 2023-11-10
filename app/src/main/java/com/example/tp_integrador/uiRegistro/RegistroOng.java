package com.example.tp_integrador.uiRegistro;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.domain.enums.RequestType;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validateJpgFiles.IValidateJpegFiles;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegistroOng extends AppCompatActivity {

    EditText editTextName;
    EditText editTextDescription;
    EditText editTextLocation;
    EditText editTextMail;
    EditText editTextPassword;
    EditText editTextPhone;

    Button guardarButton;
    Button cancelarOngButton;
    Button examinePhotoButton;
    TextView fileSelectedPhoto;

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    @Inject
    IOngSave ongSave;

    @Inject
    IValidateJpegFiles validateJpgFiles;

    private String photoFileName;

    private static final String JPG_TYPE = "image/jpeg";


    /* ******* 840 INIT LOAD LOGO ********************/
    private ActivityResultLauncher<Intent> activityResultLauncher;
    /* ******************************* */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ************************* 840 ******************/
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        /* **** 840 ******************************************/

        setContentView(R.layout.activity_registro_ong);

        editTextName = findViewById(R.id.txtOngName);
        editTextDescription = findViewById(R.id.txtOngDescription);
        editTextLocation = findViewById(R.id.txtOngLocation);
        editTextMail = findViewById(R.id.txtOngMail);
        editTextPassword = findViewById(R.id.txtOngPassword);
        editTextPhone = findViewById(R.id.txtOngPhone);
        fileSelectedPhoto = findViewById(R.id.archivoSeleccionadoPhotoOng);

        guardarButton = findViewById(R.id.btnOngGuardar);
        cancelarOngButton = findViewById(R.id.cancelarOngButton);
        examinePhotoButton = findViewById(R.id.examinePhotoOngButton);


        /* **************** 840 CLICK LOGO  *********************************/
        imageView = findViewById(R.id.clicktoUploadImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });
        /* ************* FIN CLICK LOGO */


        cancelarOngButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroOng.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        examinePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examinePhotoFile(v);
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();

                if (photoFileName == null) {
                    showMessage("Selecciono un archivo inválido, por favor utilize JPEG para la foto.");
                    return;
                }

                Ong ong = retrieveAndValidateInputsOngs(photoFileName);
                if (ong != null) {
                    usuario = retrieveAndValidateInputsUser();
                    if (usuario == null) return;
                } else return;

                SaveResult result = ongSave.save(usuario, ong);

                if (result.isSuccess()) {
                    Intent intent = new Intent(RegistroOng.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                showMessage(result.getMessage());
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(RegistroOng.this, message, Toast.LENGTH_SHORT).show();
    }


    private Ong retrieveAndValidateInputsOngs(String photoFileName) {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String location = editTextLocation.getText().toString();
        String phone = editTextPhone.getText().toString();
        String mail = editTextMail.getText().toString();

        Boolean isValidateInputs = validateInputs(name, description, location, phone, photoFileName);

        if (!isValidateInputs) {
            showMessage("Por favor completar todos los campos");
            return null;
        }

        return new Ong(null, name, description, photoFileName, phone, mail, location);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("name", editTextName.getText().toString());
        savedInstanceState.putString("description", editTextDescription.getText().toString());
        savedInstanceState.putString("mail", editTextMail.getText().toString());
        savedInstanceState.putString("password", editTextPassword.getText().toString());
        savedInstanceState.putString("phone", editTextPhone.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editTextName.setText(savedInstanceState.getString("name"));
        editTextDescription.setText(savedInstanceState.getString("description"));
        editTextMail.setText(savedInstanceState.getString("mail"));
        editTextPassword.setText(savedInstanceState.getString("password"));
        editTextPhone.setText(savedInstanceState.getString("phone"));
    }

    private Usuario retrieveAndValidateInputsUser() {
        String email = editTextMail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!validateMail(email, password)) {
            showMessage("Por favor verificar todos los campos");
            return null;
        }

        return new Usuario(email, password, new TipoUser(1));
    }


    private Boolean validateInputs(String nombre, String description, String location, String phone, String photoFileName) {
        List<String> inputs = Arrays.asList(nombre, description, location, phone, photoFileName);

        return validateInputs.apply(inputs);
    }

    private Boolean validateMail(String mail, String password) {
        return validateMail.validate(mail, password);
    }

    private void examinePhotoFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(JPG_TYPE);

        startActivityForResult(intent, RequestType.PHOTO.getValue());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestType.PHOTO.getValue()) {
            Uri selectedFileUri = data.getData();

            photoFileName = validateJpgFiles.processSelectedJpgFile(this, selectedFileUri);

            if (photoFileName == null) {
                fileSelectedPhoto.setText("Archivo inválido");
                fileSelectedPhoto.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            } else {
                fileSelectedPhoto.setText(photoFileName.substring(photoFileName.indexOf("files/")));
                fileSelectedPhoto.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            }
        }
    }
}