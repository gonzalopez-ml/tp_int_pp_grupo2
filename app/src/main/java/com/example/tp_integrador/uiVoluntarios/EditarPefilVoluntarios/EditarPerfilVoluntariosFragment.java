package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_integrador.R;

public class EditarPerfilVoluntariosFragment extends Fragment {

    private EditarPerfilVoluntariosViewModel mViewModel;

    public static EditarPerfilVoluntariosFragment newInstance() {
        return new EditarPerfilVoluntariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_perfil_voluntarios, container, false);
    }

}