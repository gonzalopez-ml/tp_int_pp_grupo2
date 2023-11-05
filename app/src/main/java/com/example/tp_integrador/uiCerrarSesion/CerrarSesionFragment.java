package com.example.tp_integrador.uiCerrarSesion;

import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tp_integrador.LoginActivity;
import com.example.tp_integrador.R;

public class CerrarSesionFragment extends Fragment {

    private CerrarSesionViewModel mViewModel;

    public static CerrarSesionFragment newInstance() {
        return new CerrarSesionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Infla la vista para este fragmento
        return inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Muestra el diálogo de confirmación después de que la vista se haya creado
        mostrarDialogoDeConfirmacion();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CerrarSesionViewModel.class);
        // TODO: Use the ViewModel
    }

    private void mostrarDialogoDeConfirmacion() {
        // Infla el layout personalizado para el diálogo
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom, null);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .create();

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // Encuentra los botones en el layout personalizado
        Button buttonYes = dialogView.findViewById(R.id.dialogButtonYes);
        Button buttonNo = dialogView.findViewById(R.id.dialogButtonNo);

        // Establece el evento de clic para el botón NO
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manejo del botón SÍ
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
                dialog.dismiss(); // Cierra el diálogo
            }
        });

        // Establece el evento de clic para el botón YES
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manejo del botón NO
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                dialog.dismiss(); // Cierra el diálogo
            }
        });

        dialog.show();
    }
}
