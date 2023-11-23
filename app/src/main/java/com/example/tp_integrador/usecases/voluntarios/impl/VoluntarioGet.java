package com.example.tp_integrador.usecases.voluntarios.impl;

import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.repository.voluntarios.IVoluntariosRepository;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioGet implements IVoluntarioGet {

    private IVoluntarioDao voluntarioDao;

    private IVoluntariosRepository voluntariosRepository;

    @Inject
    public VoluntarioGet(IVoluntarioDao voluntarioDao, IVoluntariosRepository voluntariosRepository) {
        this.voluntarioDao = voluntarioDao;
        this.voluntariosRepository = voluntariosRepository;
    }

    @Override
    public Voluntario getVoluntario(Integer idVoluntario) {
        try {
            Voluntario voluntario = voluntarioDao.get(idVoluntario).get();
            return voluntario;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Proyecto> getProjectsVoluntarioPostulado(Integer id) {
        try {
            return voluntariosRepository.getProjectsVoluntarioPostulado(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
