package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_integrador.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditarPerfilVoluntariosFragment extends Fragment {

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
                editTextName.setText(voluntario.getName());
                editTextLastName.setText(voluntario.getLastName());
                editTextDNI.setText(voluntario.getDni());
                editTextSkills.setText(voluntario.getSkills());
                editTextPhone.setText(voluntario.getPhone());
                editTextAvailability.setText(voluntario.getAvailability());
                //editTextCurriculum.setText(voluntario.getCv());
                //editTextPhoto.setText(voluntario.getPhoto());
                editTextMail.setText(voluntario.getUsuario().getMail());
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

                //mViewModel.editarVoluntario(name, lastName, dni, skills, phone, availability, mail);
            }
        });

        return rootView;
    }
}
