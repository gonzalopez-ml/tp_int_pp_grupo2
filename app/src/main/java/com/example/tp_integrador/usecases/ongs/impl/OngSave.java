package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OngSave implements IOngSave {

    private IUsuarioDao usuarioDao;
    private IOngDao ongDao;

    @Inject
    public OngSave(IUsuarioDao usuarioDao, IOngDao ongDao) {
        this.usuarioDao = usuarioDao;
        this.ongDao = ongDao;
    }

    @Override
    public SaveResult save(Usuario usuario, Ong ong) {
        Usuario usuarioGuardado = null;
        try {
            Usuario userExist = usuarioDao.get(usuario).get();
            if (userExist != null) {
                return new SaveResult(false, "El usuario ya esta registrado", usuarioGuardado.getIdUser());
            } else {
                Boolean isUserSave = usuarioDao.save(usuario).get();

                if (isUserSave) {
                    usuarioGuardado = usuarioDao.get(usuario).get();
                    Boolean isOngSave = ongDao.save(usuarioGuardado.getIdUser(), ong).get();

                    if (isOngSave) {
                        return new SaveResult(true, "Usuario Ong guardado correctamente", usuarioGuardado.getIdUser());
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "La Ong no pudo ser registrada 1", usuarioGuardado.getIdUser());
        }
        return new SaveResult(false, "La Ong no pudo ser registrada 2", usuarioGuardado.getIdUser());
    }

    @Override
    public List<Proyecto> getProjectsOngByLocation(String location, String idVoluntario) throws ExecutionException, InterruptedException {
        return ongDao.getProjectsOngByLocation(location, idVoluntario).get();
    }

}
