package com.example.tp_integrador.uiVoluntarios.busquedaPropuestasGeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BusquedaPropuestasGeoFragment extends Fragment implements ProyectoAdapterGeo.OnItemClickListener {

    private BusquedaPropuestasGeoViewModel mViewModel;

    private RecyclerView recyclerView;

    private ProyectoAdapterGeo proyectoAdapter;

    private SharedViewModel sharedViewModel;

    private Spinner spinnerLocalidadesGeo;

    private Button btnBuscar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestasgeo, container, false);
        mViewModel = new ViewModelProvider(this).get(BusquedaPropuestasGeoViewModel.class);

        spinnerLocalidadesGeo = rootView.findViewById(R.id.spinnerLocalidadesGeo);
        btnBuscar = rootView.findViewById(R.id.btnBuscarGeo);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        List<Localidad> localidades = mViewModel.getLocalidades();

        String[] nombresLocalidades = new String[localidades.size()];
        for (int i = 0; i < localidades.size(); i++) {
            nombresLocalidades[i] = localidades.get(i).getNombre();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, nombresLocalidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLocalidadesGeo.setAdapter(adapter);

        Voluntario voluntarioLogueado = sharedViewModel.getVoluntarioLiveData().getValue();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedLocalidad = spinnerLocalidadesGeo.getSelectedItem().toString();

                mViewModel.getProjectsByLocalidad(selectedLocalidad, voluntarioLogueado.getIdVoluntario().toString()).observe(getViewLifecycleOwner(), proyectos -> {
                    updateUIWithProyectos(proyectos);
                });
            }
        });

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

        recyclerView = rootView.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoAdapter = new ProyectoAdapterGeo(this);
        recyclerView.setAdapter(proyectoAdapter);

        return rootView;
    }

    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoAdapter.setProjects(proyectos);
        proyectoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Proyecto proyecto) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("proyecto", proyecto);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_nav_busquedapropuestas_to_detalleProyectoFragment, bundle);
    }
}