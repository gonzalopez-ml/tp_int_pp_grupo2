package com.example.tp_integrador.usecases.ongs.impl;
import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.usecases.ongs.IOngGet;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import android.util.Log;
public class OngGet implements IOngGet {

    private IOngDao ongDao;

    @Inject
    public OngGet(IOngDao ongDao) {
        this.ongDao = ongDao;
    }

    @Override
    public Ong getOng(Integer idOng) {
        Log.d("aviso","Pasa por aca getOng");
        try {
            Ong ong = ongDao.get(idOng).get();
            Log.d("aviso","Pasa por aca onDao");
            return ong;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
