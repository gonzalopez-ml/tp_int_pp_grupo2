package com.example.tp_integrador.uiONG.eliminarPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_integrador.R;

public class EliminarPropuestasLaboralesFragment extends Fragment {

    private EliminarPropuestasLaboralesViewModel mViewModel;

    public static EliminarPropuestasLaboralesFragment newInstance() {
        return new EliminarPropuestasLaboralesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eliminar_propuestas_laborales, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EliminarPropuestasLaboralesViewModel.class);
        // TODO: Use the ViewModel
    }

}