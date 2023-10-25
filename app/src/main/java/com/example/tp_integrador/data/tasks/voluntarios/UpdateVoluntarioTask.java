package com.example.tp_integrador.data.tasks.voluntarios;

import android.os.AsyncTask;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.domain.Voluntario;

public class UpdateVoluntarioTask extends AsyncTask<Voluntario, Void, Void> {
    private IVoluntariosRepository voluntariosRepository;

    public UpdateVoluntarioTask(IVoluntariosRepository repository) {
        voluntariosRepository = repository;
    }

    @Override
    protected Void doInBackground(Voluntario... voluntarios) {
        Voluntario voluntario = voluntarios[0];
        voluntariosRepository.update(voluntario);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Este método se ejecuta en el hilo principal después de actualizar el voluntario.
        // Puedes actualizar la interfaz de usuario aquí si es necesario.
    }
}
