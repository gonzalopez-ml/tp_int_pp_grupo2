package com.example.tp_integrador.usecases.voluntarios;

import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.ExecutionException;

public interface IVoluntarioGetByUserID {
    Voluntario getVoluntarioByUserID(Integer idUser) throws ExecutionException, InterruptedException;
}
