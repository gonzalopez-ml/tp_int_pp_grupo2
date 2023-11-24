package com.example.tp_integrador.uiVoluntarios.busquedaPropuestasGeo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoAdapterGeo extends RecyclerView.Adapter<ProyectoAdapterGeo.ProyectoViewHolder> {

    private List<Proyecto> projects = new ArrayList<>();
    private List<Proyecto> originalProjects;
    public Button botonVerProyecto;

    public interface OnItemClickListener {
        void onItemClick(Proyecto proyecto);
    }

    private final OnItemClickListener listener;

    public ProyectoAdapterGeo(OnItemClickListener listener) {
        this.listener = listener;
    }

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
        holder.nombreProyecto.setText(proyecto.getDisponibilidad());
        holder.descripcionProyeco.setText(proyecto.getDescripcion());

        holder.botonVerProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(proyecto);
            }
        });
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
        public Button botonVerProyecto;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreOng = itemView.findViewById(R.id.nombreOng);
            nombreProyecto = itemView.findViewById(R.id.nombreProyecto);
            descripcionProyeco = itemView.findViewById(R.id.descripcionProyecto);
            botonVerProyecto = itemView.findViewById(R.id.botonRechazoVoluntario);
        }
    }

    public void filter(String query) {
        projects.clear();

        if (query.isEmpty()) {
            projects.addAll(originalProjects);
        } else {
            for (Proyecto proyecto : originalProjects) {
                String nombreProyecto = proyecto.getNombre().toLowerCase();
                String disponibilidad = proyecto.getDisponibilidad().toLowerCase();

                if (nombreProyecto.contains(query.toLowerCase()) || disponibilidad.contains(query.toLowerCase())) {
                    projects.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void filterByDisponibilidad(String disponibilidad) {
        projects.clear();

        if (disponibilidad.isEmpty()) {
            if (originalProjects == null) {
                return;
            }
            projects.addAll(originalProjects);
        } else {
            if (originalProjects == null) {
                return;
            }
            for (Proyecto proyecto : originalProjects) {
                String disponibilidadProyecto = proyecto.getDisponibilidad().toLowerCase();

                if (disponibilidadProyecto.contains(disponibilidad.toLowerCase())) {
                    projects.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }

}

