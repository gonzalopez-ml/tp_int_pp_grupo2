package com.example.tp_integrador.uiONG.eliminarPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;
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
import com.example.tp_integrador.data.domain.Proyecto;
import java.util.List;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EliminarPropuestasLaboralesFragment extends Fragment implements ProyectoEliminarAdapter.OnItemClickListener,ProyectoEliminarAdapter.OnEditItemClickListener {

    private EliminarPropuestasLaboralesViewModel mViewModel;
    private RecyclerView recyclerView;
    private ProyectoEliminarAdapter proyectoEliminarAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_eliminar_propuestas_laborales, container, false);
        mViewModel = new ViewModelProvider(this).get(EliminarPropuestasLaboralesViewModel.class);

        recyclerView = rootView.findViewById(R.id.rvEliminarProyectos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoEliminarAdapter = new ProyectoEliminarAdapter(this,this);
        recyclerView.setAdapter(proyectoEliminarAdapter);

        //Se manda 1 pero aca va el id de la ONG logueada:
        mViewModel.getAllProjectsById(1).observe(getViewLifecycleOwner(),proyectos -> {
            updateUIWithProyectos(proyectos);
        });
        return rootView;
    }


    private void updateUIWithProyectos(List<Proyecto> proyectos) {
        proyectoEliminarAdapter.setProjects(proyectos);
    }
    @Override
    public void onItemClick(Proyecto proyecto) {
        Boolean isProyectoDelete = mViewModel.deleteProjectLiveData(proyecto.getIdProyecto());

        if(isProyectoDelete){
            Toast.makeText(requireContext(),"Se elimino el proyecto con exito!",Toast.LENGTH_SHORT).show();
            // Volvemos a cargar los proyectos después de eliminar uno
            mViewModel.getAllProjectsById(1).observe(getViewLifecycleOwner(), proyectos -> {
                updateUIWithProyectos(proyectos);
            });

        }else{
            Toast.makeText(requireContext(),"Error al eliminar el proyecto, intente nuevamente!",Toast.LENGTH_SHORT).show();
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