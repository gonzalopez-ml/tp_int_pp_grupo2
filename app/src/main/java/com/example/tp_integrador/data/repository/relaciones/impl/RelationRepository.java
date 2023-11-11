package com.example.tp_integrador.data.repository.relaciones.impl;

import com.example.tp_integrador.data.domain.Relacion;
import com.example.tp_integrador.data.repository.relaciones.IRelationRepository;
import com.example.tp_integrador.data.tasks.relaciones.SaveRelationTask;
import com.example.tp_integrador.data.tasks.relaciones.UpdateRelationTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Singleton;

@Singleton
public class RelationRepository implements IRelationRepository {

    @Override
    public CompletableFuture<Boolean> save(Relacion relacion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveRelationTask().execute(relacion).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> update(Integer idVoluntario, Integer idProyecto) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new UpdateRelationTask().execute(idVoluntario, idProyecto).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
