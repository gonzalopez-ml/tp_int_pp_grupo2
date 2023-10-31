package com.example.tp_integrador.data.tasks.ongs;

import android.os.AsyncTask;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

public class GetOngTask extends AsyncTask<Integer, Void, Ong> {

    @Override
    protected Ong doInBackground(Integer... ids) {
        int id = ids[0];
        return null;
    }

    @Override
    protected void onPostExecute(Ong voluntario) {
        // Este método se ejecuta en el hilo principal después de obtener el voluntario.
        // Puedes actualizar la interfaz de usuario aquí.
    }
}

