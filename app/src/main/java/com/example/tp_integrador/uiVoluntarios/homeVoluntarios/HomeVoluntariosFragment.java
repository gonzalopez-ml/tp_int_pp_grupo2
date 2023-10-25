package com.example.tp_integrador.uiVoluntarios.homeVoluntarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_integrador.databinding.FragmentHomeVoluntariosBinding;

public class HomeVoluntariosFragment extends Fragment {

    private FragmentHomeVoluntariosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeVoluntariosViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeVoluntariosViewModel.class);

        binding = FragmentHomeVoluntariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}