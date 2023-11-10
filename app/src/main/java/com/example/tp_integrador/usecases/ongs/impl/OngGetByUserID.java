package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.usecases.ongs.IOngGetByUserID;


import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OngGetByUserID implements IOngGetByUserID {

    private IOngDao ongDao;

    @Inject
    public OngGetByUserID(IOngDao ongDao) {
        this.ongDao = ongDao;
    }

    @Override
    public Ong getOngByUserID(Integer idUser) throws ExecutionException, InterruptedException {
        try {
            Ong ong = ongDao.getByUserID(idUser).get();
            return ong;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
