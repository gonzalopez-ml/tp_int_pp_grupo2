package com.example.tp_integrador.data.repository.voluntarios;

import com.example.tp_integrador.data.tasks.voluntarios.GetVoluntarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.SaveVoluntarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.UpdateVoluntarioTask;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VoluntarioRepository implements IVoluntariosRepository {

    private IVoluntariosRepository voluntariosRepository;

    @Override
    public CompletableFuture<Voluntario> get(Integer id) {
        new GetVoluntarioTask(voluntariosRepository).execute(id);
        return null;
    }

    @Override
    public CompletableFuture<Boolean> update(Voluntario voluntario) {
        new UpdateVoluntarioTask(voluntariosRepository).execute(voluntario);
        return null;
    }

    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Voluntario voluntario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveVoluntarioTask().execute(idUser, voluntario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
