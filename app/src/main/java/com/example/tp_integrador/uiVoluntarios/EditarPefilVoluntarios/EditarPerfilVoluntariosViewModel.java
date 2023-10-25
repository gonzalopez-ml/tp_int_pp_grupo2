package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditarPerfilVoluntariosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EditarPerfilVoluntariosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}