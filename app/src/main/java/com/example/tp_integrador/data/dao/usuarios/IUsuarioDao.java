package com.example.tp_integrador.data.dao.usuarios;

import com.example.tp_integrador.data.domain.Usuario;

import java.util.concurrent.CompletableFuture;

public interface IUsuarioDao {
    CompletableFuture<Usuario> get(Usuario usuario);
    CompletableFuture<Boolean> save(Usuario usuario);
}
