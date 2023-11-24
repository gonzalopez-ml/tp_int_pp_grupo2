package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BusquedaPropuestasFragment extends Fragment implements ProyectoAdapter.OnItemClickListener {

    private BusquedaPropuestasViewModel mViewModel;

    private RecyclerView recyclerView;

    private ProyectoAdapter proyectoAdapter;

    private SharedViewModel sharedViewModel;

    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestas, container, false);
        mViewModel = new ViewModelProvider(this).get(BusquedaPropuestasViewModel.class);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        SearchView searchView = rootView.findViewById(R.id.search_all_proyectsGeo);
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

        Voluntario voluntarioLogueado = sharedViewModel.getVoluntarioLiveData().getValue();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoAdapter = new ProyectoAdapter(this);
        recyclerView.setAdapter(proyectoAdapter);
        spinner = rootView.findViewById(R.id.spinner);


        String[] valoresPorDefecto = {"", "Full Time", "Part Time"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, valoresPorDefecto);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String disponibilidadSeleccionada = (String) parentView.getItemAtPosition(position);
                proyectoAdapter.filterByDisponibilidad(disponibilidadSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        mViewModel.getAllProjects(voluntarioLogueado.getIdVoluntario()).observe(getViewLifecycleOwner(), proyectos -> {
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