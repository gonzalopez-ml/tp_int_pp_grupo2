package com.example.tp_integrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.tp_integrador.databinding.ActivityMainBinding;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;
import com.example.tp_integrador.usecases.ongs.IOngGetByUserID;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tp_integrador.databinding.ActivityMainOngBinding;

import dagger.hilt.android.AndroidEntryPoint;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

@AndroidEntryPoint
public class MainActivityONG extends AppCompatActivity {

    private ActivityMainOngBinding binding;
    private String nombreUsuario;
    private String mailUsuario;

    @Inject
    IOngGetByUserID ongGetByUserID;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

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
                SharedViewModel sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
                sharedViewModel.setOng(ong); // -> Importante, setea la ong para poder usarlo en todos los fragments.
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

        drawerLayout = findViewById(R.id.drawer_layout_ongs);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_ong);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

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