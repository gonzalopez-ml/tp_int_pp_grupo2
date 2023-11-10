package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.usecases.ongs.IOngProjectDelete;

import java.util.concurrent.ExecutionException;

public class OngProjectDelete implements IOngProjectDelete {

    private final IOngDao iOngDao;

    public OngProjectDelete(IOngDao iOngDao){
        this.iOngDao =iOngDao;
    }

    @Override
    public Boolean deleteProjectOng(Integer id) throws ExecutionException, InterruptedException {
        return iOngDao.delete(id).get();
    }
}
