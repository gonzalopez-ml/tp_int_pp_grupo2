package com.example.tp_integrador.data.dao.relaciones.impl;

import com.example.tp_integrador.data.dao.relaciones.IRelationDao;
import com.example.tp_integrador.data.domain.Relacion;
import com.example.tp_integrador.data.repository.relaciones.IRelationRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class RelationDao implements IRelationDao {

    private final IRelationRepository repository;

    @Inject
    public RelationDao(IRelationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<Boolean> save(Relacion relacion) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isUserSave = repository.save(relacion);
            try {
                return isUserSave.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}
