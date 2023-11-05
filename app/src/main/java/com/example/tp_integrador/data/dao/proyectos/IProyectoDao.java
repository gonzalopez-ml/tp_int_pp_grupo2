package com.example.tp_integrador.data.dao.proyectos;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;

import java.util.concurrent.CompletableFuture;

public interface IProyectoDao {
    CompletableFuture<Proyecto> get(Integer id);
    CompletableFuture<Boolean> save(Proyecto proyecto, Integer idOng);
}
