package com.example.tp_integrador.usecases.voluntarios.impl;

import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioUpdate;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioUpdate implements IVoluntarioUpdate {

    private IUsuarioDao usuarioDao;
    private IVoluntarioDao voluntarioDao;

    @Inject
    public VoluntarioUpdate(IUsuarioDao usuarioDao, IVoluntarioDao voluntarioDao) {
        this.usuarioDao = usuarioDao;
        this.voluntarioDao = voluntarioDao;
    }

    @Override
    public Boolean update(Voluntario voluntario) {
        try {
            Boolean isVoluntarioSave = voluntarioDao.update(voluntario).get();
            if (isVoluntarioSave) {
                return usuarioDao.update(voluntario.getUsuario()).get();
            } else return false;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
