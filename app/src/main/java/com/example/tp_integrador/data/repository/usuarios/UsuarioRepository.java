package com.example.tp_integrador.data.repository.usuarios;

import com.example.tp_integrador.data.tasks.usuarios.GetAllowLoginTask;
import com.example.tp_integrador.data.tasks.usuarios.GetUsuarioTask;
import com.example.tp_integrador.data.tasks.usuarios.SaveUsuarioTask;
import com.example.tp_integrador.data.domain.Usuario;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsuarioRepository implements IUsuariosRepository {

    @Inject
    public UsuarioRepository() {
    }

    public CompletableFuture<Usuario> get(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetUsuarioTask().execute(usuario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveUsuarioTask().execute(usuario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Usuario> getAllowAccess(Usuario usuario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetAllowLoginTask().execute(usuario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
