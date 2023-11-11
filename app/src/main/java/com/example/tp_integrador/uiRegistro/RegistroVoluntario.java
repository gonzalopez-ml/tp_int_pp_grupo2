package com.example.tp_integrador.uiRegistro;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.domain.enums.RequestType;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;
import com.example.tp_integrador.utils.validateJpgFiles.IValidateJpegFiles;
import com.example.tp_integrador.utils.validatePdfFiles.IValidatePdfFiles;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class RegistroVoluntario extends AppCompatActivity {

    EditText editTextName;
    EditText editTextLastName;
    EditText editTextDni;
    EditText editTextPhone;
    EditText editTextAvailability;
    EditText editTextSkills;
    EditText editTextEmail;
    EditText editTextPassword;
    TextView fileSelectedCv;
    TextView fileSelectedPhoto;


    Button guardarButton;
    Button examineCvButton;
    Button examinePhotoButton;
    Button cancelarVolutarioButton;

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    @Inject
    IVoluntarioSave voluntarioSave;

    @Inject
    IValidatePdfFiles validatePdfFiles;

    @Inject
    IValidateJpegFiles validateJpgFiles;

    private String cvFileName;
    private String photoFileName;

    private static final String PDF_TYPE = "application/pdf";
    private static final String JPG_TYPE = "image/jpeg";

    /* ******* 840 INIT LOAD LOGO ********************/
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ImageView imageView;
    Bitmap bitmap;
    private Long idUser;

    private String logo;
    /* ******************************* */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                imageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        setContentView(R.layout.activity_registro_voluntario);

        editTextName = findViewById(R.id.nombreVoluntarioRegistro);
        editTextLastName = findViewById(R.id.apellidoVoluntarioRegistro);
        editTextDni = findViewById(R.id.dniVoluntarioRegistro);
        editTextPhone = findViewById(R.id.telefonoVoluntarioRegistro);
        editTextAvailability = findViewById(R.id.disponibilidadVoluntarioRegistro);
        editTextSkills = findViewById(R.id.habilidadesVoluntarioRegistro);
        editTextEmail = findViewById(R.id.emailVoluntario);
        editTextPassword = findViewById(R.id.passwordVoluntario);
        fileSelectedCv = findViewById(R.id.archivoSeleccionadoCv);
        fileSelectedPhoto = findViewById(R.id.archivoSeleccionadoPhoto);

        guardarButton = findViewById(R.id.guardarButton);
        examineCvButton = findViewById(R.id.examinarCvButton);
        examinePhotoButton = findViewById(R.id.examinarFotoButton);
        cancelarVolutarioButton = findViewById(R.id.cancelarVoluntarioButton);

        imageView = findViewById(R.id.clicktoUploadImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        cancelarVolutarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroVoluntario.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        examineCvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examineCvFile(view);
            }
        });

        examinePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examinePhotoFile(view);
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();

                if (cvFileName == null || photoFileName == null) {
                    showMessage("Selecciono archivos inválidos, por favor para CV utilize pdf y para la foto JPG.");
                    return;
                }

                Voluntario voluntario = retrieveAndValidateInputsVoluntarios(cvFileName, photoFileName);
                if (voluntario != null) {
                    usuario = retrieveAndValidateInputsUser();
                    if (usuario == null) return;
                } else return;


                SaveResult result = voluntarioSave.save(usuario, voluntario);

                idUser = result.getUserId();

                Log.d("Aviso","Pasa ID USUARIO:"+idUser);
                loadImg();

                Log.d("Aviso","Pasa loadimg");


                /*  ** FIN *************** */


                if (result.isSuccess()) {
                    Intent intent = new Intent(RegistroVoluntario.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                showMessage(result.getMessage());
            }

        });
    }

    // ****** 840 COMIENZA EL UPLOAD DE LA IMAGEN ***************
    private void loadImg() {

        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        final Boolean[] actionLogo = {null};

        if (bitmap==null)
        {

            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                bitmap = bitmapDrawable.getBitmap();

            }
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        final String base64Image = Base64.encodeToString(bytes,Base64.DEFAULT);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url ="https://btw.com.ar/app/upload-v.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success"))
                        {
                            //Toast.makeText(requireContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            Log.d("Aviso","Pasa luego d actionLogo[0] = true;");
                            actionLogo[0] = true;
                        } else  Toast.makeText(getApplicationContext(), "Error Image Uploaded", Toast.LENGTH_SHORT).show();

                        actionLogo[0] = false;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                actionLogo[0] = false;
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();

                Log.d("Aviso","Pasa id"+idUser);
                paramV.put("image", base64Image);
                paramV.put("usuario",String.valueOf(idUser));
                // paramV.put("fragment","O");
                // paramV.put("action","U");

                return paramV;
            }
        };
        queue.add(stringRequest);

    }
    // ******* FINALIZA UPLOAD IMAGEN ****************

    private void showMessage(String message) {
        Toast.makeText(RegistroVoluntario.this, message, Toast.LENGTH_SHORT).show();
    }

    private Voluntario retrieveAndValidateInputsVoluntarios(String cvFileName, String photoFileName) {
        String name = editTextName.getText().toString();
        String lastname = editTextLastName.getText().toString();
        String dni = editTextDni.getText().toString();
        String phone = editTextPhone.getText().toString();
        String availability = editTextAvailability.getText().toString();
        String skills = editTextSkills.getText().toString();

        Boolean isValidateInputs = validateInputs(name, lastname, dni, phone, availability, skills, cvFileName, photoFileName);

        if (!isValidateInputs) {
            showMessage("Por favor completar todos los campos");
            return null;
        }

        return new Voluntario(null, name, lastname, dni, phone, availability, skills, cvFileName, photoFileName);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("name", editTextName.getText().toString());
        savedInstanceState.putString("lastname", editTextLastName.getText().toString());
        savedInstanceState.putString("dni", editTextDni.getText().toString());
        savedInstanceState.putString("phone", editTextPhone.getText().toString());
        savedInstanceState.putString("availability", editTextAvailability.getText().toString());
        savedInstanceState.putString("skills", editTextSkills.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        editTextName.setText(savedInstanceState.getString("name"));
        editTextLastName.setText(savedInstanceState.getString("lastname"));
        editTextDni.setText(savedInstanceState.getString("dni"));
        editTextPhone.setText(savedInstanceState.getString("phone"));
        editTextAvailability.setText(savedInstanceState.getString("availability"));
        editTextSkills.setText(savedInstanceState.getString("skills"));
    }

    private Usuario retrieveAndValidateInputsUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!validateMail(email, password)) {
            showMessage("Por favor verificar todos los campos");
            return null;
        }

        return new Usuario(email, password, new TipoUser(2));
    }


    private Boolean validateInputs(String nombre, String apellido, String dni, String telefono, String dispo, String habilidad, String cvFile, String photoFile) {
        List<String> inputs = Arrays.asList(nombre, apellido, dni, telefono, dispo, habilidad, cvFile, photoFile);

        return validateInputs.apply(inputs);
    }

    private Boolean validateMail(String mail, String password) {
        return validateMail.validate(mail, password);
    }

    private void examineCvFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(PDF_TYPE);

        startActivityForResult(intent, RequestType.PDF.getValue());
    }

    private void examinePhotoFile(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType(JPG_TYPE);

        startActivityForResult(intent, RequestType.PHOTO.getValue());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestType.PDF.getValue()) {
            Uri selectedFileUri = data.getData();

            cvFileName = validatePdfFiles.processSelectedPdfFile(this, selectedFileUri);

            if (cvFileName == null) {
                fileSelectedCv.setText("Archivo inválido");
                fileSelectedCv.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            } else {
                fileSelectedCv.setText(cvFileName.substring(cvFileName.indexOf("files/")));
                fileSelectedCv.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            }
        } else {
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