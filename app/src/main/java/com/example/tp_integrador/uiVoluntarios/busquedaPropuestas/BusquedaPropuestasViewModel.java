package com.example.tp_integrador.uiVoluntarios.busquedaPropuestas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusquedaPropuestasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BusquedaPropuestasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}