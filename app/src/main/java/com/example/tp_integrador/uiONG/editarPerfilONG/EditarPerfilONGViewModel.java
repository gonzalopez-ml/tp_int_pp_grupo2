package com.example.tp_integrador.uiONG.editarPerfilONG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.usecases.ongs.IOngUpdate;
import com.example.tp_integrador.usecases.ongs.IOngGet;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarPerfilONGViewModel extends ViewModel {

    private IOngGet ongGet;

    private IOngUpdate ongUpdate;

    private IUsuarioDao usuarioDao;

    private MutableLiveData<Ong> ongLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> ongUpdateLiveData = new MutableLiveData<>();


    @Inject
    public EditarPerfilONGViewModel(IOngGet ongGet, IOngUpdate ongUpdate, IUsuarioDao usuarioDao) {
        this.ongGet = ongGet;
        this.ongUpdate = ongUpdate;
        this.usuarioDao = usuarioDao;
    }

    public EditarPerfilONGViewModel() {
    }

    public LiveData<Ong> getOngLiveData() {
        Log.d("Aviso","Entra GetOngLiveData");
        try {
            Log.d("aviso","Pasa por aca OnGet.GetOng");
            Ong ong = ongGet.getOng(24);

            if (ong != null) {
                ongLiveData.setValue(ong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ongLiveData;
    }

    public Boolean saveOngLiveData(Ong ong) {
        try {
            Boolean isOngSave = ongUpdate.update(ong);

            if (isOngSave) {
                ongUpdateLiveData.setValue(true);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}