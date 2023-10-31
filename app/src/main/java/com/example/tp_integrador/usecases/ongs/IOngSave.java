package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.utils.customMessages.SaveResult;


public interface IOngSave {
    SaveResult save(Usuario usuario, Ong ong);
}
