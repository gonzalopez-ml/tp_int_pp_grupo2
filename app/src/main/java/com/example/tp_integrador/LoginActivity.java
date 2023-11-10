package com.example.tp_integrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiRegistro.RegistroOng;
import com.example.tp_integrador.uiRegistro.RegistroVoluntario;
import com.example.tp_integrador.uiVoluntarios.homeVoluntarios.HomeVoluntariosFragment;
import com.example.tp_integrador.usecases.usuarios.ILoginAllowAccess;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;

import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioGet;
import com.example.tp_integrador.utils.customMessages.LoginResult;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    EditText editTextMail;
    EditText editTextPassword;

    Button btnLogin;

    @Inject
    IValidateInputs validateInputs;

    @Inject
    ILoginAllowAccess loginAllowAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMail = findViewById(R.id.editTxtLoginMail);
        editTextPassword = findViewById(R.id.editTxtLoginPassword);

        btnLogin = findViewById(R.id.btnIniciarSesion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = retrieveUserAndValidateInputLogin();

                if (usuario != null) {
                    Usuario userLogin = loginAllowAccess.getAllowAccess(usuario);

                    if (userLogin != null) {
                        if (userLogin.getTipoUser().getId() == 1) {
                            redirect(MainActivityONG.class, userLogin);
                        } else{
                            redirect(MainActivityVoluntarios.class, userLogin);
                        }


                    }
                    showMessage("Usuario o contraseña incorrecto");
                }
            }
        });

    }

    public void onLinkClickVoluntario(View view) {
        Intent intent = new Intent(this, RegistroVoluntario.class);
        startActivity(intent);
    }

    public void onLinkClickOng(View view) {
        Intent intent = new Intent(this, RegistroOng.class);
        startActivity(intent);
    }

    private Usuario retrieveUserAndValidateInputLogin() {
        String mail = editTextMail.getText().toString();
        String password = editTextPassword.getText().toString();

        Boolean isValidateInputs = validateInputs(mail, password);

        if (!isValidateInputs) {
            showMessage("Por favor completar todos los campos");
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setMail(mail);
        usuario.setPassword(password);
        return usuario;
    }

    private Boolean validateInputs(String mail, String password) {
        List<String> inputs = Arrays.asList(mail, password);

        return validateInputs.apply(inputs);
    }

    private void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void redirect(Class activity, Usuario userLogin) {
        showMessage("Bienvenido!");
        Intent intent = new Intent(LoginActivity.this, activity);
        intent.putExtra("usuarioLogeado", userLogin);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}



