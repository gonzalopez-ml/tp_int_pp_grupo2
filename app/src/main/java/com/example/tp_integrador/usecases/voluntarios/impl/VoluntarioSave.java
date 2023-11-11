package com.example.tp_integrador.usecases.voluntarios.impl;

import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class VoluntarioSave implements IVoluntarioSave {

    private IUsuarioDao usuarioDao;
    private IVoluntarioDao voluntarioDao;

    @Inject
    public VoluntarioSave(IUsuarioDao usuarioDao, IVoluntarioDao voluntarioDao) {
        this.usuarioDao = usuarioDao;
        this.voluntarioDao = voluntarioDao;
    }

    @Override
    public SaveResult save(Usuario usuario, Voluntario voluntario) {
        Usuario usuarioGuardado = null;
        try {
            Usuario userExist = usuarioDao.get(usuario).get();
            if (userExist != null) {
                return new SaveResult(false, "El usuario ya esta registrado", 0);
            } else {
                Boolean isUserSave = usuarioDao.save(usuario).get();

                if (isUserSave) {
                    usuarioGuardado = usuarioDao.get(usuario).get();
                    Boolean isVoluntarioSave = voluntarioDao.save(usuarioGuardado.getIdUser(), voluntario).get();

                    if (isVoluntarioSave) {
                        return new SaveResult(true, "Usuario Voluntario guardado correctamente", usuarioGuardado.getIdUser());
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "El Voluntario no pudo ser registrado", usuarioGuardado.getIdUser());
        }
        return new SaveResult(false, "El Voluntario no pudo ser registrado", usuarioGuardado.getIdUser());
    }

}
