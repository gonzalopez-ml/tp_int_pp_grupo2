package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.domain.Voluntario;

public class UpdateVoluntarioTask extends AsyncTask<Voluntario, Void, Void> {

    @Override
    protected Void doInBackground(Voluntario... voluntarios) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Este método se ejecuta en el hilo principal después de actualizar el voluntario.
        // Puedes actualizar la interfaz de usuario aquí si es necesario.
    }
}
