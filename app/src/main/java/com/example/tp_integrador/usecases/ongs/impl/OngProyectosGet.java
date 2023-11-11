package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OngProyectosGet implements IOngProyectosGet {

    private final IOngDao iOngDao;

    public OngProyectosGet(IOngDao iOngDao) {
        this.iOngDao = iOngDao;
    }

    @Override
    public List<Proyecto> getProjectsOng() throws ExecutionException, InterruptedException {
        return iOngDao.getProjectsOng().get();
    }

    @Override
    public List<Proyecto> getProjectsOngById(Integer id) throws ExecutionException, InterruptedException {
        return iOngDao.getProjectsOngById(id).get();
    }

    @Override
    public List<ProyectoVoluntario> getVoluntariosProjects(Integer idOng) throws ExecutionException, InterruptedException {
        return iOngDao.getVoluntariosProjectsOng(idOng).get();
    }
}
