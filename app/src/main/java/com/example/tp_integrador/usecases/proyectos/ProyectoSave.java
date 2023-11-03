package com.example.tp_integrador.usecases.proyectos;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.dao.proyectos.IProyectoDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class ProyectoSave implements IProyectoSave{

    private IProyectoDao proyectoDao;

    private IOngDao ongDao;


    @Inject
    public ProyectoSave(IProyectoDao proyectoDao, IOngDao ongDao){
        this.proyectoDao = proyectoDao;
        this.ongDao = ongDao;
    }

    @Override
    public SaveResult save(Proyecto proyecto, Ong ong) {
        try{
            Boolean isProyectoSave = proyectoDao.save(proyecto,ong.getIdOng()).get();

            if (isProyectoSave) {
                return new SaveResult(true, "Propuesta guardada correctamente");
            }



        } catch (ExecutionException | InterruptedException e) {
            return new SaveResult(false, "La propuesta no pudo ser registrada");
        }
        return new SaveResult(false, "La propuesta no pudo ser registrada");
    }
}
