package com.example.tp_integrador.data.dao.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOngDao {
    CompletableFuture<Ong> get(Integer id);
    CompletableFuture<Boolean> save(Integer idUser, Ong ong);
    CompletableFuture<Boolean> update(Ong ong);
    CompletableFuture<List<Proyecto>> getProjectsOng();
    CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location);
    CompletableFuture<Ong> getByUserID(Integer idUser);
}
