package com.example.tp_integrador.uiONG.editarPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_integrador.R;

public class EditarPropuestasLaboralesFragment extends Fragment {

    private EditarPropuestasLaboralesViewModel mViewModel;

    public static EditarPropuestasLaboralesFragment newInstance() {
        return new EditarPropuestasLaboralesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_propuestas_laborales, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditarPropuestasLaboralesViewModel.class);
        // TODO: Use the ViewModel
    }

}