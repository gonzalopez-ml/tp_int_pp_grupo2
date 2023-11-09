package com.example.tp_integrador.data.dao.proyectos;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.repository.proyectos.IProyectoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class ProyectoDao implements IProyectoDao {

    IProyectoRepository proyectoRepository;

    @Inject
    public ProyectoDao(IProyectoRepository proyectoRepository){
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public CompletableFuture<Proyecto> get(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            CompletableFuture<Proyecto> proyectoFuture = proyectoRepository.get(id);
            try{
                    return proyectoFuture.get();
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Proyecto proyecto) {
        return CompletableFuture.supplyAsync(()->{
            CompletableFuture<Boolean> isProyectoSave = proyectoRepository.save(proyecto);
            try{
                return isProyectoSave.get();
            }catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
            return false;
        });
    }
}
