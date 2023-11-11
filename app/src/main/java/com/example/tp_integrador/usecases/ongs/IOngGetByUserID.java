package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Ong;

import java.util.concurrent.ExecutionException;

public interface IOngGetByUserID {
    Ong getOngByUserID(Integer idUser) throws ExecutionException, InterruptedException;
}
