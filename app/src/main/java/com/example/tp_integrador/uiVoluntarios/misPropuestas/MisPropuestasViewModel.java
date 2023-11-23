package com.example.tp_integrador.uiVoluntarios.misPropuestas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MisPropuestasViewModel extends ViewModel {

    private final IVoluntarioGet voluntarioGet;

    private final MutableLiveData<List<Proyecto>> proyectosList = new MutableLiveData<>();

    @Inject
    public MisPropuestasViewModel(IVoluntarioGet voluntarioGet) {
        this.voluntarioGet = voluntarioGet;
    }

    public LiveData<List<Proyecto>> getAllProjects(Integer idVoluntario) {
        try {
            List<Proyecto> listProyects = voluntarioGet.getProjectsVoluntarioPostulado(idVoluntario);
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}