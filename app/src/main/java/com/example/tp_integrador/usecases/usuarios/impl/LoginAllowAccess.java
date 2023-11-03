package com.example.tp_integrador.usecases.usuarios.impl;

import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.usecases.usuarios.ILoginAllowAccess;
import com.example.tp_integrador.utils.customMessages.LoginResult;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class LoginAllowAccess implements ILoginAllowAccess {

    private IUsuarioDao usuarioDao;

    @Inject
    public LoginAllowAccess(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario getAllowAccess(Usuario usuario) {
        try {
            Usuario userAllowAccess = usuarioDao.getAllowAccess(usuario).get();

            if (userAllowAccess == null) {
                return null;
            }

            return userAllowAccess;

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
