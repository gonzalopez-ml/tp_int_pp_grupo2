package com.example.tp_integrador.data.domain;

public class Proyecto {

    private Integer idProyecto;
    private Ong ong;
    private String nombre;
    private String descripcion;
    private String objetivos; //necesidades
    private String disponibilidad;
    private String ubicacion;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto, Ong ong, String nombre, String descripcion, String objetivos, String ubicacion,String disponibilidad) {
        this.idProyecto = idProyecto;
        this.ong = ong;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
    }
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
