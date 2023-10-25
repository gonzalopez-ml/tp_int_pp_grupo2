package com.example.tp_integrador.uiVoluntarios.verONGsVoluntarios;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.uiVoluntarios.detallePropuestasVol.VerDetallePropuestaOng;

public class PerfilONGVoluntariosFragment extends Fragment {

    private PerfilONGVoluntariosViewModel mViewModel;

    public static PerfilONGVoluntariosFragment newInstance() {
        return new PerfilONGVoluntariosFragment();
    }

    //tuve que agregar esto porque sino no podia hacer click en ver detalle.. verificar luego
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if (view != null) {
            TextView verDetalleTextView = view.findViewById(R.id.textView23);
            if (verDetalleTextView != null) {
                verDetalleTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLinkClickVerDetalle(v);
                    }
                });
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil_o_n_g_voluntarios, container, false);
    }

    public void onLinkClickVerDetalle(View view) {
        Intent intent = new Intent(getActivity(), VerDetallePropuestaOng.class);
        startActivity(intent);
    }
}