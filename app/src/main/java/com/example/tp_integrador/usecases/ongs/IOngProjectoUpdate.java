package com.example.tp_integrador.usecases.ongs;

import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.utils.customMessages.SaveResult;

public interface IOngProjectoUpdate {

    SaveResult update(Proyecto proyecto);
}
