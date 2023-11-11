package com.example.tp_integrador.data.dao.relaciones;

import com.example.tp_integrador.data.domain.Relacion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IRelationDao {
    CompletableFuture<Boolean> save(Relacion relacion);
    CompletableFuture<Boolean> update(Integer idVoluntario, Integer idProyecto);
}
