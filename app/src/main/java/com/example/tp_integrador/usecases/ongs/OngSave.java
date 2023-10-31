package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.utils.customMessages.SaveResult;

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
        try {
            Usuario userExist = usuarioDao.get(usuario).get();
            if (userExist != null) {
                return new SaveResult(false, "El usuario ya esta registrado");
            } else {
                Boolean isUserSave = usuarioDao.save(usuario).get();

                if (isUserSave) {
                    Usuario usuarioGuardado = usuarioDao.get(usuario).get();
                    Boolean isOngSave = ongDao.save(usuarioGuardado.getIdUser(), ong).get();

                    if (isOngSave) {
                        return new SaveResult(true, "Usuario Ong guardado correctamente");
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "La Ong no pudo ser registrada");
        }
        return new SaveResult(false, "La Ong no pudo ser registrada");
    }

}
