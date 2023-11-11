package com.example.tp_integrador.uiVoluntarios.detallePropuestasVol;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.data.domain.Relacion;
import com.example.tp_integrador.usecases.relaciones.ISaveRelation;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetalleProyectoOngViewModel extends ViewModel {
    private MutableLiveData<Proyecto> proyectoLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> saveResultLiveData = new MutableLiveData<>();
    private ISaveRelation saveRelation;

    @Inject
    public DetalleProyectoOngViewModel(ISaveRelation saveRelation) {
        this.saveRelation = saveRelation;
    }

    public DetalleProyectoOngViewModel() {
    }

    public void setProyecto(Proyecto proyecto) {
        proyectoLiveData.setValue(proyecto);
    }

    public MutableLiveData<Boolean> getSaveResultLiveData() {
        return saveResultLiveData;
    }

    public Boolean saveRelation(Integer idVoluntario) throws ExecutionException, InterruptedException {
        Proyecto proyecto = proyectoLiveData.getValue();
        Relacion relacion = new Relacion();

        relacion.setIdProyecto(proyecto.getIdProyecto());
        relacion.setIdPerfilVoluntario(idVoluntario);
        relacion.setEstadoVoluntario(1);
        relacion.setEstadoRelacion(1);

        try {
            boolean saveResult = saveRelation.save(relacion);
            saveResultLiveData.postValue(saveResult);
            return saveResult;
        } catch (Exception e) {
            e.printStackTrace();
            saveResultLiveData.postValue(false);
            return false;
        }
    }

    public Boolean saveReject(Integer idVoluntario) throws ExecutionException, InterruptedException {
        Proyecto proyecto = proyectoLiveData.getValue();
        Relacion relacion = new Relacion();

        relacion.setIdProyecto(proyecto.getIdProyecto());
        relacion.setIdPerfilVoluntario(idVoluntario);
        relacion.setEstadoVoluntario(0);
        relacion.setEstadoRelacion(0);


        try {
            boolean saveResult = saveRelation.save(relacion);
            saveResultLiveData.postValue(saveResult);
            return saveResult;
        } catch (Exception e) {
            e.printStackTrace();
            saveResultLiveData.postValue(false);
            return false;
        }
    }
}
