package com.example.tp_integrador.uiONG.eliminarPropuestasLaborales;

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

public class ProyectoEliminarAdapter extends RecyclerView.Adapter<ProyectoEliminarAdapter.ProyectoEliminarViewHolder> {
    private List<Proyecto> projects = new ArrayList<>();
    private List<Proyecto> originalProjects;
    public Button botonEliminarProyecto;

    public interface OnItemClickListener {
        void onItemClick(Proyecto proyecto);
    }

    public interface OnEditItemClickListener {
        void onEditItemClick(Proyecto proyecto);
    }

    private final OnItemClickListener listener;
    private final OnEditItemClickListener editListener;


  /*  public ProyectoEliminarAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }
    */

    public ProyectoEliminarAdapter(OnItemClickListener listener, OnEditItemClickListener editListener) {
        this.listener = listener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public ProyectoEliminarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_projects2, parent, false);
        return new ProyectoEliminarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoEliminarViewHolder holder, int position) {
        Proyecto proyecto = projects.get(position);
        holder.nombreOng.setText(proyecto.getOng().getName());
        holder.nombreProyecto.setText(proyecto.getNombre());
        holder.descripcionProyeco.setText(proyecto.getDescripcion());

        holder.botonEliminarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(proyecto);
            }
        });
        holder.botonEditarProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListener.onEditItemClick(proyecto);
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

    public static class ProyectoEliminarViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreOng;
        public TextView nombreProyecto;
        public TextView descripcionProyeco;
        public Button botonEliminarProyecto;
        public Button botonEditarProyecto;

        public ProyectoEliminarViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreOng = itemView.findViewById(R.id.nombreOng);
            nombreProyecto = itemView.findViewById(R.id.nombreProyecto);
            descripcionProyeco = itemView.findViewById(R.id.descripcionProyecto);
            botonEliminarProyecto = itemView.findViewById(R.id.botonEliminarProyecto);
            botonEditarProyecto = itemView.findViewById(R.id.botonEditarProyecto);
        }
    }




}