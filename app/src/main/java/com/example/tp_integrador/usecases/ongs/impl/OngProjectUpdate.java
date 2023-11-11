package com.example.tp_integrador.usecases.ongs.impl;

import com.example.tp_integrador.data.dao.proyectos.IProyectoDao;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.ongs.IOngProjectoUpdate;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OngProjectUpdate implements IOngProjectoUpdate {

    private IProyectoDao proyectoDao;

    @Inject
    public OngProjectUpdate(IProyectoDao proyectoDao){
        this.proyectoDao = proyectoDao;
    }

    @Override
    public SaveResult update(Proyecto proyecto) {
        try{
            Boolean isProyectoSave = proyectoDao.update(proyecto).get();
            if (isProyectoSave) {
                return new SaveResult(true, "Propuesta guardada correctamente", proyecto.getIdProyecto());
            }
        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "La propuesta no pudo ser registrada", proyecto.getIdProyecto());
        }
        return new SaveResult(false, "La propuesta no pudo ser registrada", proyecto.getIdProyecto());
    }
}
