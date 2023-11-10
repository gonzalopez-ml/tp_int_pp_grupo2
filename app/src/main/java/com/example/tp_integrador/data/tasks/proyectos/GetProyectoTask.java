package com.example.tp_integrador.data.tasks.proyectos;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

public class GetProyectoTask extends AsyncTask<Integer,Void, Proyecto> {
    @Override
    protected Proyecto doInBackground(Integer... ids) {
        int id = ids[0];
        return null;
    }

    @Override
    protected void onPostExecute(Proyecto proyecto) {
        // Este método se ejecuta en el hilo principal después de obtener el proyecto.
        // Puedes actualizar la interfaz de usuario aquí.
    }
}
