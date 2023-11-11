package com.example.tp_integrador.data.dao.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;
import com.example.tp_integrador.data.tasks.ongs.GetRelationshipVoluntariosTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OngDao implements IOngDao {

    IOngRepository ongRepository;

    @Inject
    public OngDao(IOngRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    @Override
    public CompletableFuture<Ong> get(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Ong> ongFuture = ongRepository.get(id);
            try {
                return ongFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Integer idUser, Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isOngSave = ongRepository.save(idUser, ong);
            try {
                return isOngSave.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public CompletableFuture<Ong> getByUserID(Integer idUser) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Ong> ongFuture = ongRepository.getByUserID(idUser);
            try {
                return ongFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> update(Ong ong) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<Boolean> isOngUpdate = ongRepository.update(ong);
            try {
                return isOngUpdate.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }


    @Override
    public CompletableFuture<List<Proyecto>> getProjectsOng() {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<List<Proyecto>> projectsOng = ongRepository.getProjectsOng();
            try {
                return projectsOng.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<List<Proyecto>> getProjectsOngByLocation(String location) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<List<Proyecto>> projectsOng = ongRepository.getProjectsOngByLocation(location);
            try {
                return projectsOng.get();
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
}
