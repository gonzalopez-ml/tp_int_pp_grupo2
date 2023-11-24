package com.example.tp_integrador.uiVoluntarios.detallePropuestasVol;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.util.concurrent.ExecutionException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetalleProyectoOngFragment extends Fragment {

    private DetalleProyectoOngViewModel mViewModel;

    private TextView txtNombreProyecto;
    private TextView txtNecesidadesProyecto;
    private TextView txtDescripcionProyecto;
    private TextView txtUbicacionProyecto;
    private TextView txtDisponibilidadProyecto;
    private TextView txtNobreOngProyecto;
    private Button btnApply;
    private Button btnReject;
    private Voluntario loggedVoluntario;

    public static DetalleProyectoOngFragment newInstance(Proyecto proyecto) {
        DetalleProyectoOngFragment fragment = new DetalleProyectoOngFragment();
        Bundle args = new Bundle();
        args.putParcelable("proyecto", proyecto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_proyecto_ong, container, false);

        txtNombreProyecto = rootView.findViewById(R.id.txtNombreProyecto);
        txtNecesidadesProyecto = rootView.findViewById(R.id.txtNecesidadesProyecto);
        txtDescripcionProyecto = rootView.findViewById(R.id.txtDescripcionProyecto);
        txtUbicacionProyecto = rootView.findViewById(R.id.txtUbicacionProyecto);
        txtDisponibilidadProyecto = rootView.findViewById(R.id.txtDisponibilidadProyecto);
        txtNobreOngProyecto = rootView.findViewById(R.id.txtNombreOngDetalleProyecto);
        btnApply = rootView.findViewById(R.id.buttonApply);
        btnReject = rootView.findViewById(R.id.buttonReject);

        btnApply.setOnClickListener(view -> {
            try {
                SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

                sharedViewModel.getVoluntarioLiveData().observe(getViewLifecycleOwner(), voluntario -> {
                    if (voluntario != null) {
                        loggedVoluntario = new Voluntario(
                                voluntario.getIdVoluntario(),
                                voluntario.getName(),
                                voluntario.getLastName(),
                                voluntario.getDni(),
                                voluntario.getPhone(),
                                voluntario.getSkills(),
                                voluntario.getAvailability(),
                                voluntario.getCv(),
                                voluntario.getPhoto()
                        );
                    } else {
                        Log.e("DetalleProyectoOngFragment", "Voluntario es nulo");
                    }
                });
                Boolean result = mViewModel.saveRelation(loggedVoluntario.getIdVoluntario());
                if (result) {
                    showMessage("Felicitaciones, aplicaste al proyecto");
                    navigateToBusquedaPropuestasFragment();
                } else showMessage("Se produjo un error al aplicar al proyecto, intenta nuevamente mas tarde");
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        btnReject.setOnClickListener(view -> {
            try {
                SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

                sharedViewModel.getVoluntarioLiveData().observe(getViewLifecycleOwner(), voluntario -> {
                    if (voluntario != null) {
                        loggedVoluntario = new Voluntario(
                                voluntario.getIdVoluntario(),
                                voluntario.getName(),
                                voluntario.getLastName(),
                                voluntario.getDni(),
                                voluntario.getPhone(),
                                voluntario.getSkills(),
                                voluntario.getAvailability(),
                                voluntario.getCv(),
                                voluntario.getPhoto()
                        );
                    } else {
                        Log.e("DetalleProyectoOngFragment", "Voluntario es nulo");
                    }
                });

                Boolean isSave = mViewModel.saveReject(loggedVoluntario.getIdVoluntario());
                if (isSave) {
                    showMessage("Rechazaste el proyecto, no volveras a verlo");
                    navigateToBusquedaPropuestasFragment();
                } else showMessage("Se produjo un error al rechazar el proyecto, intenta nuevamente mas tarde");
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleProyectoOngViewModel.class);

        if (getArguments() != null) {
            Proyecto proyecto = getArguments().getParcelable("proyecto");
            actualizarDatosProyecto(proyecto);
        }
    }

    public void actualizarDatosProyecto(Proyecto proyecto) {
        if (proyecto != null) {
            txtNombreProyecto.setText(proyecto.getNombre());
            txtNecesidadesProyecto.setText(proyecto.getObjetivos());
            txtDescripcionProyecto.setText(proyecto.getDescripcion());
            txtUbicacionProyecto.setText(proyecto.getUbicacion());
            txtDisponibilidadProyecto.setText(proyecto.getDisponibilidad());
            txtNobreOngProyecto.setText(proyecto.getOng().getName());
            mViewModel.setProyecto(proyecto);
        }
    }

    private void showMessage(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToBusquedaPropuestasFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_busquedapropuestas);
    }
}
