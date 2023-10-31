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
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;

import java.util.Arrays;
import java.util.List;

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

    Button guardarButton;

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    @Inject
    IVoluntarioSave voluntarioSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_voluntario);

        editTextName = findViewById(R.id.nombreVoluntarioRegistro);
        editTextLastName = findViewById(R.id.apellidoVoluntarioRegistro);
        editTextDni = findViewById(R.id.dniVoluntarioRegistro);
        editTextPhone = findViewById(R.id.telefonoVoluntarioRegistro);
        editTextAvailability = findViewById(R.id.disponibilidadVoluntarioRegistro);
        editTextSkills = findViewById(R.id.habilidadesVoluntarioRegistro);
        editTextEmail = findViewById(R.id.emailVoluntario);
        editTextPassword = findViewById(R.id.passwordVoluntario);

        guardarButton = findViewById(R.id.guardarButton);

        guardarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();

                Voluntario voluntario = retrieveAndValidateInputsVoluntarios();
                if (voluntario != null) {
                    usuario = retrieveAndValidateInputsUser();
                    if (usuario == null) return;
                } else return;

                SaveResult result = voluntarioSave.save(usuario, voluntario);

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

    private void showMessage(String message) {
        Toast.makeText(RegistroVoluntario.this, message, Toast.LENGTH_SHORT).show();
    }

    private Voluntario retrieveAndValidateInputsVoluntarios() {
        String name = editTextName.getText().toString();
        String lastname = editTextLastName.getText().toString();
        String dni = editTextDni.getText().toString();
        String phone = editTextPhone.getText().toString();
        String availability = editTextAvailability.getText().toString();
        String skills = editTextSkills.getText().toString();

        Boolean isValidateInputs = validateInputs(name, lastname, dni, phone, availability, skills);

        if (!isValidateInputs) {
            showMessage("Por favor completar todos los campos");
            return null;
        }

        return new Voluntario(name, lastname, dni, phone, availability, skills, "", "");
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


    private Boolean validateInputs(String nombre, String apellido, String dni, String telefono, String dispo, String habilidad) {
        List<String> inputs = Arrays.asList(nombre, apellido, dni, telefono, dispo, habilidad);

        return validateInputs.apply(inputs);
    }

    private Boolean validateMail(String mail, String password) {
        return validateMail.validate(mail, password);
    }
}