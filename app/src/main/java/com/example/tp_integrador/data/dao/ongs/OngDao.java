package com.example.tp_integrador.data.dao.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.repository.ongs.IOngRepository;

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
}