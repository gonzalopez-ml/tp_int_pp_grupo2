package com.example.tp_integrador.uiVoluntarios.busquedaPropuestasGeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_integrador.R;
import com.example.tp_integrador.databinding.FragmentBusquedapropuestasgeoBinding;

public class BusquedaPropuestasGeoFragment extends Fragment {

    private FragmentBusquedapropuestasgeoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_busquedapropuestasgeo, container, false);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}