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

    private List<Proyecto> proyectos = new ArrayList<>();

    @NonNull
    @Override
    public ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_projects, parent, false);
        return new ProyectoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoViewHolder holder, int position) {
        Proyecto proyecto = proyectos.get(position);
        holder.nombreProyecto.setText(proyecto.getNombre());
    }

    @Override
    public int getItemCount() {
        return proyectos.size();
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
        notifyDataSetChanged();
    }

    public static class ProyectoViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreProyecto;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreProyecto = itemView.findViewById(R.id.nombreProyecto);
        }
    }
}

