package com.example.tp_integrador.uiVoluntarios.misPropuestas;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PropuestaAdapter extends RecyclerView.Adapter<PropuestaAdapter.PropuestaViewHolder> {

    private List<Proyecto> projects = new ArrayList<>();
    private List<Proyecto> originalProjects;
    public Button botonVerProyecto;

    public interface OnItemClickListener {
        void onItemClick(Proyecto proyecto);
    }

    private final OnItemClickListener listener;

    public PropuestaAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PropuestaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_projects3, parent, false);
        return new PropuestaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropuestaViewHolder holder, int position) {
        Proyecto proyecto = projects.get(position);
        holder.nombreOng.setText(proyecto.getOng().getName());
        holder.nombreProyecto.setText(proyecto.getNombre());
        holder.descripcionProyeco.setText(proyecto.getDescripcion());
        Integer estado = proyecto.getEstadoProyecto();

        String estadoS = "";
        if (estado == 1) {
            estadoS = "En revisión";
            holder.estado.setTextColor(0xFF000000);  // Restablecer a color predeterminado
            holder.estado.setText(estadoS);
        } else {
            estadoS = "Rechazado";
            holder.estado.setTextColor(0xFFFF0000);
            holder.estado.setText(estadoS);
        }

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setProjects(List<Proyecto> projects) {
        this.originalProjects = new ArrayList<>(projects);

        Collections.sort(originalProjects, new Comparator<Proyecto>() {
            @Override
            public int compare(Proyecto proyecto1, Proyecto proyecto2) {
                return Integer.compare(proyecto2.getEstadoProyecto(), proyecto1.getEstadoProyecto());
            }
        });

        this.projects = new ArrayList<>(originalProjects);
        notifyDataSetChanged();
    }

    public static class PropuestaViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreOng;
        public TextView nombreProyecto;
        public TextView descripcionProyeco;
        public TextView estado;

        public PropuestaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreOng = itemView.findViewById(R.id.nombreOngVol);
            nombreProyecto = itemView.findViewById(R.id.nombreProyectoVol);
            descripcionProyeco = itemView.findViewById(R.id.descripcionProyectoVol);
            estado = itemView.findViewById(R.id.estadoProyectoVol2);
        }
    }

    public void filter(String query) {
        projects.clear();

        if (query.isEmpty()) {
            projects.addAll(originalProjects);
        } else {
            for (Proyecto proyecto : originalProjects) {
                String nombreProyecto = proyecto.getNombre().toLowerCase();
                String estadoVoluntario = convertirEstadoAString(proyecto.getEstadoProyecto()).toLowerCase();

                if (nombreProyecto.contains(query.toLowerCase()) || estadoVoluntario.contains(query.toLowerCase())) {
                    projects.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }

    private String convertirEstadoAString(int estado) {
        return (estado == 1) ? "En revisión" : "Rechazado";
    }

    public void filterByStatus(String disponibilidad) {
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
                String estadoProyecto = convertirEstadoAString(proyecto.getEstadoProyecto());

                // Filtrar por el valor de disponibilidad ("Rechazado" o "En revisión")
                if (estadoProyecto.equals(disponibilidad)) {
                    projects.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }



}

