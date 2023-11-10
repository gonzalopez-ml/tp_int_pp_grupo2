package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Proyecto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IOngProyectosGet {
    List<Proyecto> getProjectsOng() throws ExecutionException, InterruptedException;
}
