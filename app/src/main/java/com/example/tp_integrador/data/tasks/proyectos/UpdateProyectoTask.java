package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

public class UpdateProyectoTask extends AsyncTask<Proyecto, Void, Void> {
    @Override
    protected Void doInBackground(Proyecto... proyectos) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Este método se ejecuta en el hilo principal después de actualizar el proyecto.
        // Puedes actualizar la interfaz de usuario aquí si es necesario.
    }
}
