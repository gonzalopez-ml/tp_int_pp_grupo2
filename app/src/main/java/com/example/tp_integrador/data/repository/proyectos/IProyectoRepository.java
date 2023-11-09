package com.example.tp_integrador.data.repository.proyectos;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.concurrent.CompletableFuture;

public interface IProyectoRepository {

    CompletableFuture<Proyecto> get(Integer id);

    CompletableFuture<Boolean> update(Proyecto proyecto);

    CompletableFuture<Boolean> save(Proyecto proyecto);
}
