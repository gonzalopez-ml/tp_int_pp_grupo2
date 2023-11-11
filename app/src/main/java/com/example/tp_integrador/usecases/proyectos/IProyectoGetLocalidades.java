package com.example.tp_integrador.usecases.proyectos;

import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IProyectoGetLocalidades {
   List<Localidad> getLocalidadesProyecto() throws ExecutionException, InterruptedException;
}
