package com.example.tp_integrador.usecases.proyectos;

import com.example.tp_integrador.data.domain.Ong;
import com.example.tp_integrador.data.domain.Proyecto;
import com.example.tp_integrador.utils.customMessages.SaveResult;

public interface IProyectoSave {
   SaveResult save(Proyecto proyecto, Ong ong);
}
