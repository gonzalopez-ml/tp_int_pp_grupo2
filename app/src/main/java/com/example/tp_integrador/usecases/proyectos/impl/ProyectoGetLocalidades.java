package com.example.tp_integrador.usecases.proyectos.impl;

import com.example.tp_integrador.data.dao.proyectos.IProyectoDao;
import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.usecases.proyectos.IProyectoGetLocalidades;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class ProyectoGetLocalidades implements IProyectoGetLocalidades {

    private IProyectoDao proyectoDao;
    @Inject
    public ProyectoGetLocalidades(IProyectoDao proyectoDao) {
        this.proyectoDao = proyectoDao;
    }

    @Override
    public List<Localidad> getLocalidadesProyecto() throws ExecutionException, InterruptedException {
        return proyectoDao.getLocalidades().get();
    }
}
