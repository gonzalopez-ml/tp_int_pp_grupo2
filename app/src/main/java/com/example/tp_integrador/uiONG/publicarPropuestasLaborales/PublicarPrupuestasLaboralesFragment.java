package com.example.tp_integrador.uiONG.publicarPropuestasLaborales;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios.EditarPerfilVoluntariosFragment;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PublicarPrupuestasLaboralesFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;


    private PublicarPrupuestasLaboralesViewModel mViewModel;

    private EditText editTextName;
    private EditText editTextDescripcion;
    private EditText editTextObjetivos;
    private EditText editTextDisponibilidad;
    private EditText editTextUbicacion;
    private Button btnGuardarProyecto;


    public static PublicarPrupuestasLaboralesFragment newInstance() {
        return new PublicarPrupuestasLaboralesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_publicar_prupuestas_laborales, container, false);

        mViewModel = new ViewModelProvider(this).get(PublicarPrupuestasLaboralesViewModel.class);

        editTextName= rootView.findViewById(R.id.editTextProyectoNombre);
        editTextDescripcion= rootView.findViewById(R.id.editTextProyectoDescripcion);
        editTextObjetivos = rootView.findViewById(R.id.editTextProyectoObjetivos);
        editTextDisponibilidad = rootView.findViewById(R.id.editTextProyectoDisponiblidad);
        editTextUbicacion = rootView.findViewById(R.id.editTextProyectoUbicacion);

        btnGuardarProyecto = rootView.findViewById(R.id.btnGuardarProyecto);

        btnGuardarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= editTextName.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String objetivos = editTextObjetivos.getText().toString();
                String disponiblidad = editTextDisponibilidad.getText().toString();
                String ubicacion = editTextUbicacion.getText().toString();

                Boolean isValidateInputs = validateInputsProyecto(nombre,descripcion,objetivos,disponiblidad,ubicacion);

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Ong ong = new Ong();
                    Proyecto  proyecto = new Proyecto(null,ong,nombre,descripcion,objetivos,disponiblidad,ubicacion);
                    Boolean isProyectoSave = mViewModel.saveProyectoLiveData(proyecto);

                    if(isProyectoSave){
                        Toast.makeText(requireContext(),"Se grabaron los datos con exito!",Toast.LENGTH_SHORT).show();
                        reiniciarCampos();
                    }else{
                    Toast.makeText(requireContext(),"Error al guardar los datos, intente nuevamente!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }


    private void reiniciarCampos(){
        editTextName.setText("Nombre");
        editTextUbicacion.setText("Ubicacion");
        editTextDisponibilidad.setText("Disponibilidad");
        editTextObjetivos.setText("Objetivos");
        editTextDescripcion.setText("Desecripcion");
    }

    private Boolean validateInputsProyecto(String nombre, String descripcion, String objetivos, String disponibilidad, String ubicacion) {
        List<String> inputs = Arrays.asList(nombre, descripcion,objetivos,disponibilidad,ubicacion);
        return validateInputs.apply(inputs);
    }




/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PublicarPrupuestasLaboralesViewModel.class);
        // TODO: Use the ViewModel
    }
*/
}