package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.databinding.FragmentBusquedapropuestasBinding;

public class BusquedaPropuestasFragment extends Fragment {

    private FragmentBusquedapropuestasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestas, container, false);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}