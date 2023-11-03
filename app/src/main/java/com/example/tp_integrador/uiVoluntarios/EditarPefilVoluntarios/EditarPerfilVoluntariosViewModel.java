package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarPerfilVoluntariosViewModel extends ViewModel {

    private IVoluntarioGet voluntarioGet;

    private MutableLiveData<Voluntario> voluntarioLiveData = new MutableLiveData<>();

    @Inject
    public EditarPerfilVoluntariosViewModel(IVoluntarioGet voluntarioGet) {
        this.voluntarioGet = voluntarioGet;
    }

    public EditarPerfilVoluntariosViewModel() {
    }

    public LiveData<Voluntario> getVoluntarioLiveData() {
        try {
            Voluntario voluntario = voluntarioGet.getVoluntario(17);

            if (voluntario != null) {
                voluntarioLiveData.setValue(voluntario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voluntarioLiveData;
    }
}