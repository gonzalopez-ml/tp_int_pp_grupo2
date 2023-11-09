package com.example.tp_integrador.uiONG.editarPerfilONG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.TipoUser;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import android.util.Log;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarPerfilONGFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    private EditarPerfilONGViewModel mViewModel;

    private EditText editTextName;
    private EditText editTextDescripcion;
    private EditText editTextUbicacion;
    private EditText editTextTelefono;
    private EditText editTextMail;

    private EditText editPassword;

    private Button btnEditarOng;

    private Integer idUser;
    private int idType;

    public static EditarPerfilONGFragment newInstance() {
        return new EditarPerfilONGFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("Aviso","Pasa Frame 0");
        View rootView = inflater.inflate(R.layout.fragment_editar_perfil_o_n_g, container, false);

        mViewModel = new ViewModelProvider(this).get(EditarPerfilONGViewModel.class);

        Log.d("Aviso","Pasa Frame 1");
        editTextName = rootView.findViewById(R.id.txtOngName);
        editTextDescripcion = rootView.findViewById(R.id.txtOngDescription);
        editTextUbicacion = rootView.findViewById(R.id.txtOngLocation);
        editTextMail = rootView.findViewById(R.id.txtOngMail);
        editTextTelefono = rootView.findViewById(R.id.txtOngPhone);
        //editTextLogo = rootView.findViewById(R.id.txtOngl);
        //editTextMail = rootView.findViewById(R.id.editTxtMailEditVoluntario);
        editPassword = rootView.findViewById(R.id.txtOngPassword);

        btnEditarOng = rootView.findViewById(R.id.btnOngGuardar);
        Log.d("Aviso","Pasa Frame 2");

        Log.d("Aviso","Pasa Frame clean");
        mViewModel.getOngLiveData().observe(getViewLifecycleOwner(), ong -> {

            Log.d("Aviso","Pasa Frame 3");
            if (ong != null) {
                Log.d("Aviso","Pasa Frame 4");
                idUser = ong.getUsuario().getIdUser();
                idType = ong.getUsuario().getTipoUser().getId();
                editTextName.setText(ong.getName());
                editTextDescripcion.setText(ong.getDescription());
                editTextUbicacion.setText(ong.getLocation());
                editTextMail.setText(ong.getUsuario().getMail());
                editTextTelefono.setText(ong.getPhone());
                editPassword.setText(ong.getUsuario().getPassword());
            }
        });

        btnEditarOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Aviso","Pasa Frame 5");

                String name = editTextName.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String ubicacion = editTextUbicacion.getText().toString();
                String mail = editTextMail.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String contraseña = editPassword.getText().toString();


                Boolean isValidateInputs = validateInputsOng(name, descripcion, ubicacion, mail, telefono, contraseña);

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    TipoUser tipoUser = new TipoUser(idType);
                    Usuario usuario = new Usuario(mail, contraseña, tipoUser);
                    usuario.setIdUser(idUser);
                    Ong ong = new Ong(1, name, descripcion, "", telefono, mail, ubicacion);
                    ong.setUsuario(usuario);

                    Boolean isOngSave = mViewModel.saveOngLiveData(ong);

                    if (isOngSave) {
                        Toast.makeText(requireContext(), "Se editaron los datos con éxito!", Toast.LENGTH_SHORT).show();
                        reloadFragment();
                    } else {
                        Toast.makeText(requireContext(), "Error al guardar la ONG, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private void reloadFragment() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_editar_perfil_ong, new EditarPerfilONGFragment());
        ft.commit();
    }

    private Boolean validateInputsOng(String name, String descripcion, String ubicacion, String mail, String telefono, String contraseña) {
        List<String> inputs = Arrays.asList(name, descripcion, ubicacion, mail, telefono, contraseña);

        Boolean isValidateInputs = validateInputs.apply(inputs);

        if (isValidateInputs) {
            return validateMail.validate(mail, contraseña);
        }
        return false;
    }

}