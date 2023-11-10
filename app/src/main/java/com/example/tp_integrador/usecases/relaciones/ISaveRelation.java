package com.example.tp_integrador.usecases.relaciones;

import com.example.tp_integrador.data.domain.Relacion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface ISaveRelation {
    Boolean save(Relacion relacion) throws ExecutionException, InterruptedException;
}
