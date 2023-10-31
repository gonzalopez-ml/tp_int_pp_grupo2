package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;

public class UpdateOngTask extends AsyncTask<Ong, Void, Void> {

    @Override
    protected Void doInBackground(Ong... ongs) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Este método se ejecuta en el hilo principal después de actualizar el voluntario.
        // Puedes actualizar la interfaz de usuario aquí si es necesario.
    }
}
