package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IOngProyectosGet {
    List<Proyecto> getProjectsOng(Integer idVoluntario) throws ExecutionException, InterruptedException;
    List<Proyecto> getProjectsOngById(Integer id) throws ExecutionException, InterruptedException;
    List<ProyectoVoluntario> getVoluntariosProjects(Integer idOng) throws ExecutionException, InterruptedException;
}
