package com.example.tp_integrador.data.repository.proyectos;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.tasks.proyectos.GetProyectoTask;
import com.example.tp_integrador.data.tasks.proyectos.SaveProyectoTask;
import com.example.tp_integrador.data.tasks.proyectos.UpdateProyectoTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProyectoRepository implements IProyectoRepository{

    @Override
    public CompletableFuture<Proyecto> get(Integer id) {
        new GetProyectoTask().execute(id);
        return null;
    }

    @Override
    public CompletableFuture<Boolean> update(Proyecto proyecto) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new UpdateProyectoTask().execute(proyecto).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Boolean> save(Proyecto proyecto) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return new SaveProyectoTask().execute(proyecto).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
