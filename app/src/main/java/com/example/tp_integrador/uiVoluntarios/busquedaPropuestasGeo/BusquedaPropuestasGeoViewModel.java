package com.example.tp_integrador.uiVoluntarios.busquedaPropuestasGeo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusquedaPropuestasGeoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BusquedaPropuestasGeoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}