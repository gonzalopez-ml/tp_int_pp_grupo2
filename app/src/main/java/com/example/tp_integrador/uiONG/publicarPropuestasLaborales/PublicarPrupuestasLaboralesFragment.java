package com.example.tp_integrador.uiONG.publicarPropuestasLaborales;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_integrador.R;

public class PublicarPrupuestasLaboralesFragment extends Fragment {

    private PublicarPrupuestasLaboralesViewModel mViewModel;

    public static PublicarPrupuestasLaboralesFragment newInstance() {
        return new PublicarPrupuestasLaboralesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publicar_prupuestas_laborales, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PublicarPrupuestasLaboralesViewModel.class);
        // TODO: Use the ViewModel
    }

}