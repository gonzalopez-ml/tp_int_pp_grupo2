package com.example.tp_integrador.uiONG.homeONG;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeOngViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeOngViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}