package com.example.tp_integrador.data.repository.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.tasks.ongs.GetOngTask;
import com.example.tp_integrador.data.tasks.ongs.SaveOngTask;
import com.example.tp_integrador.data.tasks.ongs.UpdateOngTask;
import com.example.tp_integrador.data.tasks.voluntarios.GetVoluntarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.UpdateVoluntarioTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import android.util.Log;

public class OngRepository implements IOngRepository {


    @Override
    public CompletableFuture<Ong> get(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Log.d("Aviso", "GetOngTask");
                return new GetOngTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }


    @Override
    public CompletableFuture<Boolean> update(Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new UpdateOngTask().execute(ong).get();

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }


    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveOngTask().execute(idUser, ong).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
