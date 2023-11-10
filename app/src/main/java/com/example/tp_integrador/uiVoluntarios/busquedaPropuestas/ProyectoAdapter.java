package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ProyectoViewHolder> {

    private List<Proyecto> projects = new ArrayList<>();
    private List<Proyecto> originalProjects;

    @NonNull
    @Override
    public ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_projects, parent, false);
        return new ProyectoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoViewHolder holder, int position) {
        Proyecto proyecto = projects.get(position);
        holder.nombreOng.setText(proyecto.getOng().getName());
        holder.nombreProyecto.setText(proyecto.getNombre());
        holder.descripcionProyeco.setText(proyecto.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setProjects(List<Proyecto> projects) {
        this.projects = projects;
        this.originalProjects = new ArrayList<>(projects);
        notifyDataSetChanged();
    }

    public static class ProyectoViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreOng;
        public TextView nombreProyecto;
        public TextView descripcionProyeco;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreOng = itemView.findViewById(R.id.nombreOng);
            nombreProyecto = itemView.findViewById(R.id.nombreProyecto);
            descripcionProyeco = itemView.findViewById(R.id.descripcionProyecto);
        }
    }

    public void filter(String query) {
        projects.clear();

        if (query.isEmpty()) {
            projects.addAll(originalProjects);
        } else {
            for (Proyecto proyecto : originalProjects) {
                if (proyecto.getNombre().toLowerCase().contains(query.toLowerCase())) {
                    projects.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }

}

