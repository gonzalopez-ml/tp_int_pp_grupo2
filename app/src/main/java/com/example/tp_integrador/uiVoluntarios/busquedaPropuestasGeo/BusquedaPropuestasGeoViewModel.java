package com.example.tp_integrador.uiVoluntarios.busquedaPropuestasGeo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Localidad;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;
import com.example.tp_integrador.usecases.ongs.IOngSave;
import com.example.tp_integrador.usecases.proyectos.IProyectoGetLocalidades;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BusquedaPropuestasGeoViewModel extends ViewModel {

    private final IOngProyectosGet ongProyectosGet;

    private final MutableLiveData<List<Proyecto>> proyectosList = new MutableLiveData<>();

    private final IProyectoGetLocalidades proyectoGetLocalidades;

    private final IOngSave ongSave;


    @Inject
    public BusquedaPropuestasGeoViewModel(IOngProyectosGet ongProyectosGet, IProyectoGetLocalidades proyectoGetLocalidades,
                                          IOngSave ongSave) {
        this.ongProyectosGet = ongProyectosGet;
        this.proyectoGetLocalidades = proyectoGetLocalidades;
        this.ongSave = ongSave;
    }

    public LiveData<List<Proyecto>> getAllProjects(Integer idVoluntario) {
        try {
            List<Proyecto> listProyects = ongProyectosGet.getProjectsOng(idVoluntario);
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    public LiveData<List<Proyecto>> getProjectsByLocalidad(String localidad, String idVoluntario) {
        try {
            List<Proyecto> listProyects = ongSave.getProjectsOngByLocation(localidad, idVoluntario);
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}