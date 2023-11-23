package com.example.tp_integrador.data.repository.voluntarios;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IVoluntariosRepository {
    CompletableFuture<Voluntario> get(Integer id);
    CompletableFuture<Boolean> update(Voluntario voluntario);
    CompletableFuture<Boolean> save(Integer idUser, Voluntario voluntario);
    CompletableFuture<Voluntario> getByUserID(Integer idUser);
    CompletableFuture<List<Proyecto>> getProjectsVoluntarioPostulado(Integer id);
}
