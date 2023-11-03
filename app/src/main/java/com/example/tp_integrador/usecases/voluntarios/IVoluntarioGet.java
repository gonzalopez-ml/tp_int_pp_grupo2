package com.example.tp_integrador.usecases.voluntarios;

import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IVoluntarioGet {
    Voluntario getVoluntario(Integer idVoluntario) throws ExecutionException, InterruptedException;
}
