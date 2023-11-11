package com.example.tp_integrador.data.domain;

import java.io.Serializable;

public class Zona implements Serializable {
    int idZona;
    String nombre;

    public Integer getId() {
        return idZona;
    }
    public String getNombre(){return nombre;}
    public void setId(int idZona) {
        this.idZona = idZona;
    }
    public void setNombre(String nombre){this.nombre = nombre;}

    public Zona(int idZona) {
        this.idZona = idZona;
    }

    public Zona() {
    }
}
