package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public interface IOngSave {
    SaveResult save(Usuario usuario, Ong ong);
    List<Proyecto> getProjectsOngByLocation(String location, String idVoluntario) throws ExecutionException, InterruptedException;
}
