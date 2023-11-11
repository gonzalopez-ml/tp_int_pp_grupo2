package com.example.tp_integrador.data.repository.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOngRepository {
    CompletableFuture<Ong> get(Integer id);
    CompletableFuture<Boolean> update(Ong ong);
    CompletableFuture<Boolean> save(Integer idUser, Ong ong);
    CompletableFuture<Boolean> delete(Integer id);
    CompletableFuture<Ong> getByUserID(Integer idUser);
    CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location, String idVoluntario);
    CompletableFuture<List<ProyectoVoluntario>> getVoluntariosProjectsOng(Integer idOng);
    CompletableFuture<List<Proyecto>> getProjectsOng(Integer id);
    CompletableFuture<List<Proyecto>> getProjectsOngWithouthRelation(Integer id);
}
