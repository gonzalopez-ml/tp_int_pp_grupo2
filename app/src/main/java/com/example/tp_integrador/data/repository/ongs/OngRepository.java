package com.example.tp_integrador.data.repository.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.data.tasks.ongs.DeleteProjectOngTask;
import com.example.tp_integrador.data.tasks.ongs.GetOngByUserIDTask;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.tasks.ongs.GetOngTask;
import com.example.tp_integrador.data.tasks.ongs.GetProjectsOngByIdPerfilOngTask;
import com.example.tp_integrador.data.tasks.ongs.GetProjectsOngByLocationTask;
import com.example.tp_integrador.data.tasks.ongs.GetProjectsOngTask;
import com.example.tp_integrador.data.tasks.ongs.GetRelationshipVoluntariosTask;
import com.example.tp_integrador.data.tasks.ongs.SaveOngTask;
import com.example.tp_integrador.data.tasks.ongs.UpdateOngTask;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OngRepository implements IOngRepository {

    @Override
    public CompletableFuture<Ong> get(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetOngTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> update(Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new UpdateOngTask().execute(ong).get();

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveOngTask().execute(idUser, ong).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new DeleteProjectOngTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Ong> getByUserID(Integer idUser) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetOngByUserIDTask().execute(idUser).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Proyecto>> getProjectsOng() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetProjectsOngTask().execute().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetProjectsOngByLocationTask().execute(location).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<ProyectoVoluntario>> getVoluntariosProjectsOng(Integer idOng) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetRelationshipVoluntariosTask().execute(idOng).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Proyecto>> getProjectsOng(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new GetProjectsOngByIdPerfilOngTask().execute(id).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
