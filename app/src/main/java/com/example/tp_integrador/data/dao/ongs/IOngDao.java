package com.example.tp_integrador.data.dao.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.concurrent.CompletableFuture;

public interface IOngDao {
    CompletableFuture<Ong> get(Integer id);
    CompletableFuture<Boolean> save(Integer idUser, Ong ong);

    CompletableFuture<Boolean> update(Ong ong);
}
