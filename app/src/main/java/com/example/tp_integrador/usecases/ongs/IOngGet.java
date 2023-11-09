package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Ong;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IOngGet {
    Ong getOng(Integer idOng) throws ExecutionException, InterruptedException;
}