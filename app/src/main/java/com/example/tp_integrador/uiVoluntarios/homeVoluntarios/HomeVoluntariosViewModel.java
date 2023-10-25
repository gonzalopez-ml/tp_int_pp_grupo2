package com.example.tp_integrador.uiVoluntarios.homeVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeVoluntariosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeVoluntariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Voluntarios");
    }

    public LiveData<String> getText() {
        return mText;
    }
}