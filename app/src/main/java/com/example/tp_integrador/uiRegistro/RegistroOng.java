package com.example.tp_integrador.uiRegistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;

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

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    @Inject
    IOngSave ongSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ong);

        editTextName = findViewById(R.id.txtOngName);
        editTextDescription = findViewById(R.id.txtOngDescription);
        editTextLocation = findViewById(R.id.txtOngLocation);
        editTextMail = findViewById(R.id.txtOngMail);
        editTextPassword = findViewById(R.id.txtOngPassword);
        editTextPhone = findViewById(R.id.txtOngPhone);

        guardarButton = findViewById(R.id.btnOngGuardar);

        guardarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();

                Ong ong = retrieveAndValidateInputsOngs();
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


    private Ong retrieveAndValidateInputsOngs() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String location = editTextLocation.getText().toString();
        String phone = editTextPhone.getText().toString();
        String mail = editTextMail.getText().toString();

        Boolean isValidateInputs = validateInputs(name, description, location, phone);

        if (!isValidateInputs) {
            showMessage("Por favor completar todos los campos");
            return null;
        }

        return new Ong(null, name, description, "", location, mail, phone);
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


    private Boolean validateInputs(String nombre, String description, String location, String phone) {
        List<String> inputs = Arrays.asList(nombre, description, location, phone);

        return validateInputs.apply(inputs);
    }

    private Boolean validateMail(String mail, String password) {
        return validateMail.validate(mail, password);
    }
}