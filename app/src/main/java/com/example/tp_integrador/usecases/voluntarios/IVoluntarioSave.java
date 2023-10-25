package com.example.tp_integrador.usecases.voluntarios;

import com.example.tp_integrador.data.domain.Usuario;
import com.example.tp_integrador.data.domain.Voluntario;
import com.example.tp_integrador.utils.customMessages.SaveResult;


public interface IVoluntarioSave {
    SaveResult save(Usuario usuario, Voluntario voluntario);
}
