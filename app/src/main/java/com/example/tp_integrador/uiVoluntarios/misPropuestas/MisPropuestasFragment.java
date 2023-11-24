package com.example.tp_integrador.uiVoluntarios.misPropuestas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MisPropuestasFragment extends Fragment implements PropuestaAdapter.OnItemClickListener {

    private MisPropuestasViewModel mViewModel;

    private RecyclerView recyclerView;

    private PropuestaAdapter proyectoAdapter;

    private SharedViewModel sharedViewModel;

    private Spinner spinner;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mis_propuestas, container, false);
        mViewModel = new ViewModelProvider(this).get(MisPropuestasViewModel.class);
        spinner = rootView.findViewById(R.id.spinner2MISPROP);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        SearchView searchView = rootView.findViewById(R.id.search_all_proyectsVol);
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


        String[] valoresPorDefecto = {"", "Rechazado", "En revisión"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, valoresPorDefecto);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String disponibilidadSeleccionada = (String) parentView.getItemAtPosition(position);
                proyectoAdapter.filterByStatus(disponibilidadSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        Voluntario voluntarioLogueado = sharedViewModel.getVoluntarioLiveData().getValue();

        recyclerView = rootView.findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoAdapter = new PropuestaAdapter(this);
        recyclerView.setAdapter(proyectoAdapter);

        mViewModel.getAllProjects(voluntarioLogueado.getIdVoluntario()).observe(getViewLifecycleOwner(), proyectos -> {
            updateUIWithProyectos(proyectos);
        });

        return rootView;
    }

    @Override
    public void onItemClick(Proyecto proyecto) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("proyecto", proyecto);
        //NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        //navController.navigate(R.id.action_nav_busquedapropuestas_to_detalleProyectoFragment, bundle);
    }

    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoAdapter.setProjects(proyectos);
    }

    private void navigateToBusquedaPropuestasFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_busquedapropuestas);
    }
}