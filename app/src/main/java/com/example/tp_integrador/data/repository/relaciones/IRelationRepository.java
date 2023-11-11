package com.example.tp_integrador.data.repository.relaciones;

import com.example.tp_integrador.data.domain.Relacion;

import java.util.concurrent.CompletableFuture;

public interface IRelationRepository {
    CompletableFuture<Boolean> save(Relacion relacion);
    CompletableFuture<Boolean> update(Integer idVoluntario, Integer idProyecto);
}
