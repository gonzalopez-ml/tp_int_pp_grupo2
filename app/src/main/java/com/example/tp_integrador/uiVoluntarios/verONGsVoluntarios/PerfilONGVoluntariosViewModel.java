package com.example.tp_integrador.uiVoluntarios.verONGsVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerfilONGVoluntariosViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<String> mText;

    public PerfilONGVoluntariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Perfil ONG Voluntarios");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

