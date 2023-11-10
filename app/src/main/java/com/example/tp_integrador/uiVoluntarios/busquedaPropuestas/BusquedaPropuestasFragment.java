package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BusquedaPropuestasFragment extends Fragment {

    private BusquedaPropuestasViewModel mViewModel;

    private RecyclerView recyclerView;

    private ProyectoAdapter proyectoAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestas, container, false);
        mViewModel = new ViewModelProvider(this).get(BusquedaPropuestasViewModel.class);


        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoAdapter = new ProyectoAdapter();
        recyclerView.setAdapter(proyectoAdapter);

        mViewModel.getAllProjects().observe(getViewLifecycleOwner(), proyectos -> {
            updateUIWithProyectos(proyectos);
        });

        return rootView;
    }

    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoAdapter.setProyectos(proyectos);
    }
}