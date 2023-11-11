package com.example.tp_integrador.uiONG.eliminarPropuestasLaborales;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.ongs.IOngProjectDelete;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EliminarPropuestasLaboralesViewModel extends ViewModel {

    private final IOngProyectosGet ongProyectosGet;

    private final IOngProjectDelete ongProjectDelete;

    private final MutableLiveData<List<Proyecto>> proyectosList = new MutableLiveData<>();

    @Inject
    public EliminarPropuestasLaboralesViewModel(IOngProyectosGet ongProyectosGet,IOngProjectDelete ongProjectDelete){
        this.ongProyectosGet = ongProyectosGet;
        this.ongProjectDelete = ongProjectDelete;
    }

    public LiveData<List<Proyecto>> getAllProjectsById(Integer id) {
        try {
            List<Proyecto> listProyects = ongProyectosGet.getProjectsOngById(id);
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean deleteProjectLiveData(Integer id){
        try{
            Boolean isProjectDelete = ongProjectDelete.deleteProjectOng(id);
        if(isProjectDelete){
            return true;
        }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}