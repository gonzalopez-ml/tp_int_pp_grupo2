package com.example.tp_integrador.uiONG.verPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.util.List;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VerPropuestasLaboralesFragment extends Fragment implements ProyectoVerAdapter.OnItemClickListener, ProyectoVerAdapter.OnEditItemClickListener {

    private VerPropuestasLaboralesViewModel mViewModel;
    private RecyclerView recyclerView;
    private ProyectoVerAdapter proyectoVerAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_ver_propuestas_laborales, container, false);
        mViewModel = new ViewModelProvider(this).get(VerPropuestasLaboralesViewModel.class);

        recyclerView = rootView.findViewById(R.id.rvEliminarProyectos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoVerAdapter = new ProyectoVerAdapter(this,this);
        recyclerView.setAdapter(proyectoVerAdapter);

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Ong ong = sharedViewModel.getOng().getValue();
        mViewModel.getAllProjectsById(ong.getIdOng()).observe(getViewLifecycleOwner(),proyectos -> {
            updateUIWithProyectos(proyectos);
        });
        return rootView;
    }


    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoVerAdapter.setProjects(proyectos);
    }
    @Override
    public void onItemClick(Proyecto proyecto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmar Eliminación");
        builder.setMessage("¿Está seguro que desea eliminar este proyecto?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProyecto(proyecto);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void deleteProyecto(Proyecto proyecto) {
        Boolean isProyectoDelete = mViewModel.deleteProjectLiveData(proyecto.getIdProyecto());
        Ong ong = proyecto.getOng();

        if (isProyectoDelete) {
            Toast.makeText(requireContext(), "Se eliminó el proyecto con éxito!", Toast.LENGTH_SHORT).show();
            mViewModel.getAllProjectsById(ong.getIdOng()).observe(getViewLifecycleOwner(), proyectos -> {
                updateUIWithProyectos(proyectos);
            });
        } else {
            Toast.makeText(requireContext(), "Error al eliminar el proyecto, inténtelo nuevamente!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEditItemClick(Proyecto proyecto) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("proyecto", proyecto);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main_activity_ong);
        navController.navigate(R.id.action_nav_eliminarPropuestas_to_editarPropueestasFragment, bundle);

    }
}