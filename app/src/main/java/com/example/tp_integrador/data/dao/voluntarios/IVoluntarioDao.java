package com.example.tp_integrador.data.dao.voluntarios;

import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.CompletableFuture;

public interface IVoluntarioDao {
    CompletableFuture<Voluntario> get(Integer id);
    CompletableFuture<Boolean> save(Integer idUser, Voluntario voluntario);
    CompletableFuture<Boolean> update(Voluntario voluntario);
}
