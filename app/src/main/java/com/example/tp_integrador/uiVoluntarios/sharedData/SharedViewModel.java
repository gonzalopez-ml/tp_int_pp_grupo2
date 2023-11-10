package com.example.tp_integrador.uiVoluntarios.sharedData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.domain.Voluntario;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Voluntario> voluntarioLiveData = new MutableLiveData<>();

    public void setVoluntario(Voluntario voluntario) {
        voluntarioLiveData.setValue(voluntario);
    }

    public LiveData<Voluntario> getVoluntarioLiveData() {
        return voluntarioLiveData;
    }
}
