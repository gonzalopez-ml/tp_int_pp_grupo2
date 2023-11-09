package com.example.tp_integrador.uiONG.homeONG;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeOngViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeOngViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ONG - Buscando la Transformación Social");
    }

    public LiveData<String> getText() {
        return mText;
    }
}