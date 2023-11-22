package com.example.tp_integrador.data.dao.usuarios;

import com.example.tp_integrador.data.repository.usuarios.IUsuariosRepository;
import com.example.tp_integrador.data.domain.Usuario;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class UsuarioDao implements IUsuarioDao {

    IUsuariosRepository usuariosRepository;

    @Inject
    public UsuarioDao(IUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public CompletableFuture<Usuario> get(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Usuario> usuarioFuture = usuariosRepository.get(usuario);
            try {
                return usuarioFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isUserSave = usuariosRepository.save(usuario);
            try {
                return isUserSave.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public CompletableFuture<Boolean> update(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isUserSave = usuariosRepository.update(usuario);
            try {
                return isUserSave.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public CompletableFuture<Usuario> getAllowAccess(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Usuario> usuarioFuture = usuariosRepository.getAllowAccess(usuario);
            try {
                return usuarioFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
