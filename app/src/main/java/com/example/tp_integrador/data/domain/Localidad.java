package com.example.tp_integrador.data.domain;

import java.io.Serializable;

public class Localidad implements Serializable {
    private int idLocalidad;
    private String nombre;
    private Zona zona;
    public Integer getId() {
        return idLocalidad;
    }
    public Zona getZona() {
        return zona;
    }
    public String getNombre(){return nombre;}
    public void setId(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
    public void setZona(Zona zona) {this.zona = zona;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public Localidad(int idLocalidad, Zona zona, String nombre) {
        this.idLocalidad = idLocalidad;
        this.zona = zona;
        this.nombre = nombre;
    }

    public Localidad() {
    }
}
