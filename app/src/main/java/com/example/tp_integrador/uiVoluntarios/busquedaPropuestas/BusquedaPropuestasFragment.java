package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.MainActivityVoluntarios;
import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.uiVoluntarios.detallePropuestasVol.DetalleProyectoOngFragment;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BusquedaPropuestasFragment extends Fragment implements ProyectoAdapter.OnItemClickListener {

    private BusquedaPropuestasViewModel mViewModel;

    private RecyclerView recyclerView;

    private ProyectoAdapter proyectoAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestas, container, false);
        mViewModel = new ViewModelProvider(this).get(BusquedaPropuestasViewModel.class);

        SearchView searchView = rootView.findViewById(R.id.search_all_proyects);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                proyectoAdapter.filter(newText);
                return true;
            }
        });


        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoAdapter = new ProyectoAdapter(this);
        recyclerView.setAdapter(proyectoAdapter);

        mViewModel.getAllProjects().observe(getViewLifecycleOwner(), proyectos -> {
            updateUIWithProyectos(proyectos);
        });

        return rootView;
    }

    @Override
    public void onItemClick(Proyecto proyecto) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("proyecto", proyecto);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_nav_busquedapropuestas_to_detalleProyectoFragment, bundle);
    }

    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoAdapter.setProjects(proyectos);
    }
}