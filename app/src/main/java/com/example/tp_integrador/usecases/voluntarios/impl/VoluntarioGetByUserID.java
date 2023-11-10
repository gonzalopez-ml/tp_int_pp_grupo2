package com.example.tp_integrador.usecases.voluntarios.impl;

import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGetByUserID;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioGetByUserID implements IVoluntarioGetByUserID {

    private IVoluntarioDao voluntarioDao;

    @Inject
    public VoluntarioGetByUserID(IVoluntarioDao voluntarioDao) {
        this.voluntarioDao = voluntarioDao;
    }

    @Override
    public Voluntario getVoluntarioByUserID(Integer idUser) throws ExecutionException, InterruptedException {
        try {
            Voluntario voluntario = voluntarioDao.getByUserID(idUser).get();
            return voluntario;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
