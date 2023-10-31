package com.example.tp_integrador.usecases.usuarios;

import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.utils.customMessages.LoginResult;

import java.util.concurrent.CompletableFuture;

public interface ILoginAllowAccess {
    Usuario getAllowAccess(Usuario usuario);
}
