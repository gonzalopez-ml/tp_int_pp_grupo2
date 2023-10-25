package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.domain.Voluntario;

public class GetVoluntarioTask extends AsyncTask<Integer, Void, Voluntario> {

    private IVoluntariosRepository voluntariosRepository;

    public GetVoluntarioTask(IVoluntariosRepository repository) {
        voluntariosRepository = repository;
    }

    @Override
    protected Voluntario doInBackground(Integer... ids) {
        int id = ids[0];
        return null;
    }

    @Override
    protected void onPostExecute(Voluntario voluntario) {
        // Este método se ejecuta en el hilo principal después de obtener el voluntario.
        // Puedes actualizar la interfaz de usuario aquí.
    }
}

