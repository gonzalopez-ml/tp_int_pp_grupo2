package com.example.tp_integrador.data.repository.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOngRepository {
    CompletableFuture<Ong> get(Integer id);
    CompletableFuture<Boolean> update(Ong ong);
    CompletableFuture<Boolean> save(Integer idUser, Ong ong);
    CompletableFuture<Ong> getByUserID(Integer idUser);
    CompletableFuture<List<Proyecto>> getProjectsOng();
    CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location);
}
