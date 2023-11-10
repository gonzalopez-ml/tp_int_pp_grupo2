package com.example.tp_integrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.ongs.IOngGetByUserID;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_integrador.databinding.ActivityMainOngBinding;

import java.util.concurrent.ExecutionException;

import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivityONG extends AppCompatActivity {

    private ActivityMainOngBinding binding;
    private String nombreUsuario;
    private String mailUsuario;
    @Inject
    IOngGetByUserID ongGetByUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainOngBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityOng.toolbar);

        View navHeaderView = binding.navView.getHeaderView(0);

        TextView textViewNombreUsuario = navHeaderView.findViewById(R.id.textViewNombreOng);
        TextView textViewMailUsuario = navHeaderView.findViewById(R.id.textViewMailOng);

        Intent intent = getIntent();
        if (intent != null) {
            Ong ong;
            Usuario userLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogeado");
            try {
                ong = ongGetByUserID.getOngByUserID(userLogin.getIdUser());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nombreUsuario = ong.getName();
            mailUsuario = ong.getMail();

            textViewNombreUsuario.setText(nombreUsuario);
            textViewMailUsuario.setText(mailUsuario);
        }

        binding.appBarMainActivityOng.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_o_n_g, menu);
        return true;
    }

}