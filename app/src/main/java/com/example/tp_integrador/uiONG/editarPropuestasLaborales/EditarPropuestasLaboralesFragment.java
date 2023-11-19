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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private Spinner spinnerDisponibilidad;
    private Button btnGuardarProyecto;
    private Button btnCancelarProyecto;
    private String ubicacion;


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
        spinnerDisponibilidad = rootView.findViewById(R.id.spinnerProyectoDisponiblidad);
        btnGuardarProyecto = rootView.findViewById(R.id.btnGuardarProyecto);
        btnCancelarProyecto = rootView.findViewById(R.id.btnCancelarProyecto);

        String[] disponibilidades = new String[2];

        disponibilidades[0] = "Part time";
        disponibilidades[1] = "Full time";

        ArrayAdapter<String> adapterDisponibilidad = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, disponibilidades);
        adapterDisponibilidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDisponibilidad.setAdapter(adapterDisponibilidad);


        Bundle bundle = getArguments();
        if (bundle != null) {
            Proyecto proyecto = bundle.getParcelable("proyecto");

            if (proyecto != null) {

                editTextName.setText(proyecto.getNombre());
                editTextDescripcion.setText(proyecto.getDescripcion());
                editTextObjetivos.setText(proyecto.getObjetivos());
                // Encuentra la posición del elemento seleccionado en el array
                int seleccionado = Arrays.asList(disponibilidades).indexOf(proyecto.getDisponibilidad());

                // Configura la selección en el Spinner
                spinnerDisponibilidad.setSelection(seleccionado);
                ubicacion = proyecto.getUbicacion();
                proyectoModificado = proyecto;
             }
        }


        btnGuardarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proyectoModificado.setNombre(editTextName.getText().toString());
                proyectoModificado.setDescripcion(editTextDescripcion.getText().toString());
                proyectoModificado.setObjetivos(editTextObjetivos.getText().toString());
                proyectoModificado.setDisponibilidad(spinnerDisponibilidad.getSelectedItem().toString());
                proyectoModificado.setUbicacion(ubicacion);
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