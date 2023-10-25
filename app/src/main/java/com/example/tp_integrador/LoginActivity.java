package com.example.tp_integrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tp_integrador.uiRegistro.RegistroOng;
import com.example.tp_integrador.uiRegistro.RegistroVoluntario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLinkClickVoluntario(View view) {
        Intent intent = new Intent(this, RegistroVoluntario.class);
        startActivity(intent);
    }

    public void onLinkClickOng(View view) {
        Intent intent = new Intent(this, RegistroOng.class);
        startActivity(intent);
    }
}



