package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.dao.ongs.IOngDao;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BusquedaPropuestasViewModel extends ViewModel {

    private final IOngProyectosGet ongProyectosGet;

    private final MutableLiveData<List<Proyecto>> proyectosList = new MutableLiveData<>();

    @Inject
    public BusquedaPropuestasViewModel(IOngProyectosGet ongProyectosGet) {
        this.ongProyectosGet = ongProyectosGet;
    }

    public LiveData<List<Proyecto>> getAllProjects() {
        try {
            List<Proyecto> listProyects = ongProyectosGet.getProjectsOng();
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}