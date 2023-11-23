package com.example.tp_integrador.data.repository.voluntarios;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.tasks.ongs.GetProjectsOngByIdPerfilOngTask;
import com.example.tp_integrador.data.tasks.proyectos.GetProyectosVoluntariosTask;
import com.example.tp_integrador.data.tasks.usuarios.UpdateUsuarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.GetVoluntarioByUserIDTask;
import com.example.tp_integrador.data.tasks.voluntarios.GetVoluntarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.SaveVoluntarioTask;
import com.example.tp_integrador.data.tasks.voluntarios.UpdateVoluntarioTask;
import com.example.tp_integrador.data.domain.Voluntario;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VoluntarioRepository implements IVoluntariosRepository {

    @Override
    public CompletableFuture<Voluntario> get(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetVoluntarioTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> update(Voluntario voluntario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new UpdateVoluntarioTask().execute(voluntario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Voluntario voluntario) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveVoluntarioTask().execute(idUser, voluntario).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Voluntario> getByUserID(Integer idUser) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetVoluntarioByUserIDTask().execute(idUser).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Proyecto>> getProjectsVoluntarioPostulado(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetProyectosVoluntariosTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
