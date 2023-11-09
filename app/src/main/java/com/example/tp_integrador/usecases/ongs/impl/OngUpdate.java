package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.usecases.ongs.IOngUpdate;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OngUpdate implements IOngUpdate {

    private IUsuarioDao usuarioDao;
    private IOngDao ongDao;

    @Inject
    public OngUpdate(IUsuarioDao usuarioDao, IOngDao ongDao) {
        this.usuarioDao = usuarioDao;
        this.ongDao = ongDao;
    }

    @Override
    public Boolean update(Ong ong) {
        try {
            Boolean isOngSave = ongDao.update(ong).get();
            if (isOngSave) {
                return usuarioDao.update(ong.getUsuario()).get();
            } else return false;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}