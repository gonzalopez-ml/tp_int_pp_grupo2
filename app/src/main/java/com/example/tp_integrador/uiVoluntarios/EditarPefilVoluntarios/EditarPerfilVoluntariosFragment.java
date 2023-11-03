package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

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
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.utils.validarCamposVacios.IValidateInputs;
import com.example.tp_integrador.utils.validarUsuario.IValidateMail;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarPerfilVoluntariosFragment extends Fragment {

    @Inject
    IValidateInputs validateInputs;

    @Inject
    IValidateMail validateMail;

    private EditarPerfilVoluntariosViewModel mViewModel;

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextDNI;
    private EditText editTextSkills;
    private EditText editTextPhone;
    private EditText editTextAvailability;
    private EditText editTextCurriculum;
    private EditText editTextPhoto;
    private EditText editTextMail;
    private EditText editPassword;

    private Button btnEditarVoluntario;

    private Integer idUser;
    private int idType;

    public static EditarPerfilVoluntariosFragment newInstance() {
        return new EditarPerfilVoluntariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editar_perfil_voluntarios, container, false);

        mViewModel = new ViewModelProvider(this).get(EditarPerfilVoluntariosViewModel.class);

        editTextName = rootView.findViewById(R.id.editTextNameVoluntario);
        editTextLastName = rootView.findViewById(R.id.editTextLastNameVoluntario);
        editTextDNI = rootView.findViewById(R.id.editTextDniVoluntario);
        editTextPhone = rootView.findViewById(R.id.editTextPhoneVolntario);
        editTextAvailability = rootView.findViewById(R.id.editTxtDispoVoluntario);
        editTextSkills = rootView.findViewById(R.id.editTxtHabilidadesVoluntario);
        editTextMail = rootView.findViewById(R.id.editTxtMailEditVoluntario);
        editPassword = rootView.findViewById(R.id.editTxtContraVoluntario);

        btnEditarVoluntario = rootView.findViewById(R.id.btnEditEditarVoluntario);

        mViewModel.getVoluntarioLiveData().observe(getViewLifecycleOwner(), voluntario -> {
            if (voluntario != null) {
                idUser = voluntario.getUsuario().getIdUser();
                idType = voluntario.getUsuario().getTipoUser().getId();
                editTextName.setText(voluntario.getName());
                editTextLastName.setText(voluntario.getLastName());
                editTextDNI.setText(voluntario.getDni());
                editTextSkills.setText(voluntario.getSkills());
                editTextPhone.setText(voluntario.getPhone());
                editTextAvailability.setText(voluntario.getAvailability());
                //editTextCurriculum.setText(voluntario.getCv());
                //editTextPhoto.setText(voluntario.getPhoto());
                editTextMail.setText(voluntario.getUsuario().getMail());
                editPassword.setText(voluntario.getUsuario().getPassword());
            }
        });

        btnEditarVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String dni = editTextDNI.getText().toString();
                String skills = editTextSkills.getText().toString();
                String phone = editTextPhone.getText().toString();
                String availability = editTextAvailability.getText().toString();
                String mail = editTextMail.getText().toString();
                String contraseña = editPassword.getText().toString();

                Boolean isValidateInputs = validateInputsVoluntarios(name, lastName, dni, skills, phone, availability, mail, contraseña);

                if (!isValidateInputs) {
                    Toast.makeText(requireContext(), "Por favor verificar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    TipoUser tipoUser = new TipoUser(idType);
                    Usuario usuario = new Usuario(mail, contraseña, tipoUser);
                    usuario.setIdUser(idUser);
                    Voluntario voluntario = new Voluntario(17, name, lastName, dni, phone, skills, availability, "", "");
                    voluntario.setUsuario(usuario);

                    Boolean isVoluntarioSave = mViewModel.saveVoluntarioLiveData(voluntario);

                    if (isVoluntarioSave) {
                        Toast.makeText(requireContext(), "Se editaron los datos con éxito!", Toast.LENGTH_SHORT).show();
                        reloadFragment();
                    } else {
                        Toast.makeText(requireContext(), "Error al guardar el voluntario, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    private void reloadFragment() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_editar_perfil_voluntarios, new EditarPerfilVoluntariosFragment());
        ft.commit();
    }

    private Boolean validateInputsVoluntarios(String name, String lastName, String dni, String skills, String phone,
                                              String availability, String mail,String contraseña) {
        List<String> inputs = Arrays.asList(name, lastName, dni, dni, skills, phone, availability, mail, contraseña);

        Boolean isValidateInputs = validateInputs.apply(inputs);

        if (isValidateInputs) {
            return validateMail.validate(mail, contraseña);
        }
        return false;
    }

}
