package com.example.tp_integrador.uiONG.verVoluntarios;

import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.uiVoluntarios.sharedData.SharedViewModel;

import java.io.File;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VerVoluntariosFragment extends Fragment {

    private VerVoluntariosViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    private RecyclerView recyclerView;
    private ProyectoVoluntarioAdapter proyectoVoluntarioAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ver_voluntarios, container, false);
        mViewModel = new ViewModelProvider(this).get(VerVoluntariosViewModel.class);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        recyclerView = rootView.findViewById(R.id.recyclerViewOngs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        proyectoVoluntarioAdapter = new ProyectoVoluntarioAdapter();
        recyclerView.setAdapter(proyectoVoluntarioAdapter);

        proyectoVoluntarioAdapter.setOnItemClickListener(new ProyectoVoluntarioAdapter.OnItemClickListener() {
            @Override
            public void onRechazoButtonClick(ProyectoVoluntario proyectoVoluntario) {
                mViewModel.updateRelation(proyectoVoluntario.getVoluntario().getIdVoluntario(), proyectoVoluntario.getProyecto().getIdProyecto()).observe(getViewLifecycleOwner(), isUpdate -> {
                    if (isUpdate) {
                        Toast.makeText(requireContext(), "Rechazaste voluntario con éxito, no estará más disponible", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.nav_ver_voluntarios);
                    } else {
                        Toast.makeText(requireContext(), "Se produjo un error, vuelve a intentarlo mas tarde.", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireView());
                        navController.navigate(R.id.nav_ver_voluntarios);
                    }
                });
            }

            @Override
            public void onVerCvButtonClick(ProyectoVoluntario proyectoVoluntario) {
                String cvPath = proyectoVoluntario.getVoluntario().getCv();

                if (cvPath != null && !cvPath.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    File file = new File(cvPath);

                    if (file.exists()) {
                        Uri contentUri = FileProvider.getUriForFile(requireContext(), "com.example.tp_integrador.fileprovider", file);

                        intent.setDataAndType(contentUri, "application/pdf");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(requireContext(), "No hay aplicaciones para abrir el archivo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "El archivo no existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "El CV no está disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Ong ong = sharedViewModel.getOng().getValue();
        if (ong != null && ong.getIdOng() != null) {
            int idOng = ong.getIdOng();
            mViewModel.getAllVoluntariosProjects(idOng).observe(getViewLifecycleOwner(), this::updateUIWithProyectos);
        }

        return rootView;
    }

    private void updateUIWithProyectos(List<ProyectoVoluntario> proyectos) {
        proyectoVoluntarioAdapter.setProyectoVoluntarioPairs(proyectos);
    }
}
