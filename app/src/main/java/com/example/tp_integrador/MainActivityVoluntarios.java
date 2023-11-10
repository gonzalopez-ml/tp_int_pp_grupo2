package com.example.tp_integrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGetByUserID;

import com.example.tp_integrador.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutionException;

import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivityVoluntarios extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private String nombreUsuario;
    private String mailUsuario;
    @Inject
    IVoluntarioGetByUserID voluntarioGetByUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        View navHeaderView = binding.navView.getHeaderView(0);

        TextView textViewNombreUsuario = navHeaderView.findViewById(R.id.textViewNombreVol);
        TextView textViewMailUsuario = navHeaderView.findViewById(R.id.textViewMailVol);

        Intent intent = getIntent();
        if (intent != null) {
            Voluntario voluntario;
            Usuario userLogin = (Usuario) getIntent().getSerializableExtra("usuarioLogeado");
            try {
                 voluntario = voluntarioGetByUserID.getVoluntarioByUserID(userLogin.getIdUser());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nombreUsuario = voluntario.getName() + " " + voluntario.getLastName();
            mailUsuario = userLogin.getMail();

            textViewNombreUsuario.setText(nombreUsuario);
            textViewMailUsuario.setText(mailUsuario);
        }
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Inicializa el DrawerLayout y el ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout_voluntarios);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Habilita el botón de hamburguesa en la barra de herramientas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Configura la navegación
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // Resto del código

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}