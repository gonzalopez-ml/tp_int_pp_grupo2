package com.example.tp_integrador.usecases.voluntarios;

import com.example.tp_integrador.data.dao.voluntarios.IVoluntarioDao;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
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
        Usuario userExist = new Usuario();
        Boolean isUserSave = false;
        try {
            userExist = usuarioDao.get(usuario).get();
            if (userExist != null) {
                return new SaveResult(false, "El usuario ya esta registrado");
            } else {
                isUserSave = usuarioDao.save(usuario).get();

                if (isUserSave) {
                    Usuario usuarioGuardado = usuarioDao.get(usuario).get();
                    Boolean isVoluntarioSave = voluntarioDao.save(usuarioGuardado.getIdUser(), voluntario).get();

                    if (isVoluntarioSave) {
                        return new SaveResult(true, "Usuario Voluntario guardado correctamente");
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "El Voluntario no pudo ser registrado");
        }
        return new SaveResult(false, "El Voluntario no pudo ser registrado");
    }

}
