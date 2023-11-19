package com.example.tp_integrador.uiONG.publicarPropuestasLaborales;

import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.proyectos.IProyectoGetLocalidades;
import com.example.tp_integrador.usecases.proyectos.IProyectoSave;
import com.example.tp_integrador.usecases.proyectos.impl.ProyectoGetLocalidades;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PublicarPrupuestasLaboralesViewModel extends ViewModel {

    private IProyectoSave proyectoSave;
    private IProyectoGetLocalidades proyectoGetLocalidades;

    @Inject
    public PublicarPrupuestasLaboralesViewModel(IProyectoSave proyectoSave, IProyectoGetLocalidades proyectoGetLocalidades){
        this.proyectoSave = proyectoSave;
        this.proyectoGetLocalidades = proyectoGetLocalidades;
    }


    public Boolean saveProyectoLiveData(Proyecto proyecto){
        try{
            Boolean isProyectoSave= proyectoSave.save(proyecto).isSuccess();//revisar ONG ( la ong se guarda antes porque es el Usuario ONG.

            if(isProyectoSave){
               return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Localidad> getLocalidades(){
        try{
            return proyectoGetLocalidades.getLocalidadesProyecto();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}