package com.example.tp_integrador.uiONG.editarPropuestasLaborales;

import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.ongs.IOngProjectoUpdate;
import com.example.tp_integrador.usecases.proyectos.IProyectoSave;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarPropuestasLaboralesViewModel extends ViewModel {


    private IOngProjectoUpdate proyectoUpdate;

    @Inject
    public EditarPropuestasLaboralesViewModel(IOngProjectoUpdate proyectoUpdate){
        this.proyectoUpdate =proyectoUpdate;
    }


    public Boolean updateProyectoLiveData(Proyecto proyectoModificado) {
        try{
            Boolean isProyectoSave= proyectoUpdate.update(proyectoModificado).isSuccess();
            if(isProyectoSave){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}