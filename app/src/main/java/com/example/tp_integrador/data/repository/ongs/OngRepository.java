package com.example.tp_integrador.data.repository.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.tasks.ongs.GetOngTask;
import com.example.tp_integrador.data.tasks.ongs.SaveOngTask;
import com.example.tp_integrador.data.tasks.ongs.UpdateOngTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OngRepository implements IOngRepository {

    @Override
    public CompletableFuture<Ong> get(Integer id) {
        new GetOngTask().execute(id);
        return null;
    }

    @Override
    public CompletableFuture<Boolean> update(Ong ong) {
        new UpdateOngTask().execute(ong);
        return null;
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
