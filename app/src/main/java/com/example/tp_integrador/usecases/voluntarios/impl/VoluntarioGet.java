package com.example.tp_integrador.usecases.voluntarios.impl;

import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioGet implements IVoluntarioGet {

    private IVoluntarioDao voluntarioDao;

    @Inject
    public VoluntarioGet(IVoluntarioDao voluntarioDao) {
        this.voluntarioDao = voluntarioDao;
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
}
