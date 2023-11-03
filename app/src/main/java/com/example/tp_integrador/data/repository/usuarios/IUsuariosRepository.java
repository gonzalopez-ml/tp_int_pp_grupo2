package com.example.tp_integrador.data.repository.usuarios;

import com.example.tp_integrador.data.domain.Usuario;

import java.util.concurrent.CompletableFuture;

public interface IUsuariosRepository {
    CompletableFuture<Usuario> get(Usuario usuario);
    CompletableFuture<Boolean> save(Usuario usuario);
    CompletableFuture<Usuario> getAllowAccess(Usuario usuario);
    CompletableFuture<Boolean> update(Usuario usuario);
}
