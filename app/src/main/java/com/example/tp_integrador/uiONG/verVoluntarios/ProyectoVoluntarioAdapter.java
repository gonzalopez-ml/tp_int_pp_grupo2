package com.example.tp_integrador.uiONG.verVoluntarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_integrador.R;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;

import java.util.ArrayList;
import java.util.List;

public class ProyectoVoluntarioAdapter extends RecyclerView.Adapter<ProyectoVoluntarioAdapter.ViewHolder> {

    private List<ProyectoVoluntario> proyectoVoluntarioPairs = new ArrayList<>();
    private List<ProyectoVoluntario> originalProyectos;

    public interface OnItemClickListener {
        void onRechazoButtonClick(ProyectoVoluntario proyectoVoluntario);
        void onVerCvButtonClick(ProyectoVoluntario proyectoVoluntario);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_relations_voluntarios, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProyectoVoluntario proyectoVoluntario = proyectoVoluntarioPairs.get(position);

        holder.txtNombreProyecto.setText(proyectoVoluntario.getProyecto().getNombre());
        holder.txtNecesidadesProyecto.setText(proyectoVoluntario.getProyecto().getObjetivos());
        holder.txtNombreVoluntario.setText(proyectoVoluntario.getVoluntario().getName());

        holder.botonRechazoVoluntario.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onRechazoButtonClick(proyectoVoluntario);
            }
        });

        holder.btnVerCv.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onVerCvButtonClick(proyectoVoluntario);
            }
        });
    }

    @Override
    public int getItemCount() {
        return proyectoVoluntarioPairs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNombreProyecto;
        public TextView txtNecesidadesProyecto;
        public TextView txtNombreVoluntario;
        public Button botonRechazoVoluntario;
        public Button btnVerCv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreProyecto = itemView.findViewById(R.id.projectName);
            txtNecesidadesProyecto = itemView.findViewById(R.id.necesidadesProyecto);
            txtNombreVoluntario = itemView.findViewById(R.id.nameVoluntario);
            botonRechazoVoluntario = itemView.findViewById(R.id.botonRechazoVoluntario);
            btnVerCv = itemView.findViewById(R.id.btnVerCv);
        }
    }

    public void setProyectoVoluntarioPairs(List<ProyectoVoluntario> proyectos) {
        this.proyectoVoluntarioPairs = proyectos;
        this.originalProyectos = new ArrayList<>(proyectos);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        proyectoVoluntarioPairs.clear();

        if (query.isEmpty()) {
            proyectoVoluntarioPairs.addAll(originalProyectos);
        } else {
            for (ProyectoVoluntario proyecto : originalProyectos) {
                if (proyecto.getProyecto().getNombre().toLowerCase().contains(query.toLowerCase())) {
                    proyectoVoluntarioPairs.add(proyecto);
                }
            }
        }

        notifyDataSetChanged();
    }
}
