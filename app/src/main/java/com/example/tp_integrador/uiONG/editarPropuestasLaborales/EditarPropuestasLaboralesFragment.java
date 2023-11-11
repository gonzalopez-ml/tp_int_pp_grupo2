package com.example.tp_integrador.uiONG.editarPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.uiONG.publicarPropuestasLaborales.PublicarPrupuestasLaboralesViewModel;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarPropuestasLaboralesFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;

    private EditarPropuestasLaboralesViewModel mViewModel;
    private EditText editTextName;
    private EditText editTextDescripcion;
    private EditText editTextObjetivos;
    private EditText editTextDisponibilidad;
    private EditText editTextUbicacion;
    private Button btnGuardarProyecto;
    private Button btnCancelarProyecto;

    private Proyecto proyectoModificado = new Proyecto();

    public static EditarPropuestasLaboralesFragment newInstance() {
        return new EditarPropuestasLaboralesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editar_propuestas_laborales, container, false);
        mViewModel = new ViewModelProvider(this).get(EditarPropuestasLaboralesViewModel.class);

        editTextName= rootView.findViewById(R.id.editTextProyectoNombre);
        editTextDescripcion= rootView.findViewById(R.id.editTextProyectoDescripcion);
        editTextObjetivos = rootView.findViewById(R.id.editTextProyectoObjetivos);
        editTextDisponibilidad = rootView.findViewById(R.id.editTextProyectoDisponiblidad);
        editTextUbicacion = rootView.findViewById(R.id.editTextProyectoUbicacion);
        btnGuardarProyecto = rootView.findViewById(R.id.btnGuardarProyecto);
        btnCancelarProyecto = rootView.findViewById(R.id.btnCancelarProyecto);


        Bundle bundle = getArguments();
        if (bundle != null) {
            Proyecto proyecto = bundle.getParcelable("proyecto");

            // Hacer algo con el objeto Proyecto
            if (proyecto != null) {

                editTextName.setText(proyecto.getNombre());
                editTextDescripcion.setText(proyecto.getDescripcion());
                editTextObjetivos.setText(proyecto.getObjetivos());
                editTextDisponibilidad.setText(proyecto.getDisponibilidad());
                editTextUbicacion.setText(proyecto.getUbicacion());
                proyectoModificado = proyecto;
             }
        }


        btnGuardarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proyectoModificado.setNombre(editTextName.getText().toString());
                proyectoModificado.setDescripcion(editTextDescripcion.getText().toString());
                proyectoModificado.setObjetivos(editTextObjetivos.getText().toString());
                proyectoModificado.setDisponibilidad(editTextDisponibilidad.getText().toString());
                proyectoModificado.setUbicacion(editTextUbicacion.getText().toString());
                Boolean isValidateInputs = validateInputsProyecto(proyectoModificado.getNombre(),proyectoModificado.getDescripcion(),proyectoModificado.getObjetivos(),proyectoModificado.getDisponibilidad(),proyectoModificado.getUbicacion());

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean isProyectoSave = mViewModel.updateProyectoLiveData(proyectoModificado);
                    if(isProyectoSave){
                        Toast.makeText(requireContext(),"Se grabaron los datos con exito!",Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main_activity_ong);
                        navController.navigate(R.id.action_nav_editarPropuestas_to_verPropuestasFragment);
                    }else{
                        Toast.makeText(requireContext(),"Error al guardar los datos, intente nuevamente!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancelarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main_activity_ong);
                navController.navigate(R.id.action_nav_editarPropuestas_to_verPropuestasFragment);
            }
        });


        return rootView;
    }


    private Boolean validateInputsProyecto(String nombre, String descripcion, String objetivos, String disponibilidad, String ubicacion) {
        List<String> inputs = Arrays.asList(nombre, descripcion,objetivos,disponibilidad,ubicacion);
        return validateInputs.apply(inputs);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditarPropuestasLaboralesViewModel.class);
        // TODO: Use the ViewModel
    }

}