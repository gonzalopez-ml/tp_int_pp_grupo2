package com.example.tp_integrador.uiONG.publicarPropuestasLaborales;

import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.proyectos.IProyectoSave;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PublicarPrupuestasLaboralesViewModel extends ViewModel {

    private IProyectoSave proyectoSave;

    @Inject
    public PublicarPrupuestasLaboralesViewModel(IProyectoSave proyectoSave){
        this.proyectoSave = proyectoSave;
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

}