package com.example.tp_integrador.uiVoluntarios.EditarPefilVoluntarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_integrador.data.dao.usuarios.IUsuarioDao;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioGet;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioSave;
import com.example.tp_integrador.usecases.voluntarios.IVoluntarioUpdate;
import com.example.tp_integrador.usecases.voluntarios.impl.VoluntarioSave;
import com.example.tp_integrador.utils.customMessages.SaveResult;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditarPerfilVoluntariosViewModel extends ViewModel {

    private IVoluntarioGet voluntarioGet;

    private IVoluntarioUpdate voluntarioUpdate;

    private IUsuarioDao usuarioDao;

    private MutableLiveData<Voluntario> voluntarioLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> voluntarioUpdateLiveData = new MutableLiveData<>();


    @Inject
    public EditarPerfilVoluntariosViewModel(IVoluntarioGet voluntarioGet, IVoluntarioUpdate voluntarioUpdate, IUsuarioDao usuarioDao) {
        this.voluntarioGet = voluntarioGet;
        this.voluntarioUpdate = voluntarioUpdate;
        this.usuarioDao = usuarioDao;
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

    public Boolean saveVoluntarioLiveData(Voluntario voluntario) {
        try {
            Boolean isVoluntarioSave = voluntarioUpdate.update(voluntario);

            if (isVoluntarioSave) {
                voluntarioUpdateLiveData.setValue(true);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}