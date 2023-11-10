package com.example.tp_integrador.usecases.relaciones.impl;

import com.example.tp_integrador.data.dao.relaciones.IRelationDao;
import com.example.tp_integrador.data.domain.Relacion;
import com.example.tp_integrador.usecases.relaciones.ISaveRelation;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class SaveRelation implements ISaveRelation {

    private final IRelationDao relationDao;

    @Inject
    public SaveRelation(IRelationDao relationDao) {
        this.relationDao = relationDao;
    }

    @Override
    public Boolean save(Relacion relacion) throws ExecutionException, InterruptedException {
        return relationDao.save(relacion).get();
    }
}
