package com.example.tp_integrador.uiONG.verVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.ProyectoVoluntario;
import com.example.tp_integrador.usecases.ongs.IOngProyectosGet;
import com.example.tp_integrador.usecases.relaciones.ISaveRelation;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VerVoluntariosViewModel extends ViewModel {

    private final IOngProyectosGet ongProyectosGet;
    private final ISaveRelation saveRelation;

    private final MutableLiveData<List<ProyectoVoluntario>> proyectosList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> saveResultLiveData = new MutableLiveData<>();

    @Inject
    public VerVoluntariosViewModel(IOngProyectosGet ongProyectosGet, ISaveRelation saveRelation) {
        this.ongProyectosGet = ongProyectosGet;
        this.saveRelation = saveRelation;
    }

    public LiveData<List<ProyectoVoluntario>> getAllVoluntariosProjects(Integer idOng) {
        try {
            List<ProyectoVoluntario> listProyects = ongProyectosGet.getVoluntariosProjects(idOng);
            proyectosList.setValue(listProyects);
            return proyectosList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public LiveData<Boolean> updateRelation(Integer idVoluntario, Integer idProyecto) {
        try {
            boolean saveResult = saveRelation.update(idVoluntario, idProyecto);
            saveResultLiveData.setValue(saveResult);
            return saveResultLiveData;
        } catch (Exception e) {
            e.printStackTrace();
            saveResultLiveData.setValue(false);
            return saveResultLiveData;
        }
    }
}
