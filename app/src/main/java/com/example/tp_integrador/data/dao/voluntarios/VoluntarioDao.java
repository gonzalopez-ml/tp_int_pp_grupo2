package com.example.tp_integrador.data.dao.voluntarios;

import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioDao implements IVoluntarioDao {

    IVoluntariosRepository voluntariosRepository;

    @Inject
    public VoluntarioDao(IVoluntariosRepository voluntariosRepository) {
        this.voluntariosRepository = voluntariosRepository;
    }

    @Override
    public CompletableFuture<Voluntario> get(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Voluntario> voluntarioFuture = voluntariosRepository.get(id);
            try {
                return voluntarioFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });    }

    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Voluntario voluntario) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isVoluntarioSave = voluntariosRepository.save(idUser, voluntario);
            try {
                return isVoluntarioSave.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}
