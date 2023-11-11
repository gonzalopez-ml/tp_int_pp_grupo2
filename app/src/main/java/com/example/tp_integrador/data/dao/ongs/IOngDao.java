package com.example.tp_integrador.data.dao.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOngDao {
    CompletableFuture<Ong> get(Integer id);
    CompletableFuture<Boolean> save(Integer idUser, Ong ong);
    CompletableFuture<Boolean> update(Ong ong);
    CompletableFuture<Boolean> delete(Integer id);
    CompletableFuture<List<Proyecto>> getProjectsOng(Integer idVoluntario);
    CompletableFuture<List<Proyecto>> getProjectsOngById(Integer id);
    CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location);
    CompletableFuture<Ong> getByUserID(Integer idUser);
    CompletableFuture<List<ProyectoVoluntario>> getVoluntariosProjectsOng(Integer idOng);
}
