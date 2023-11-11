package com.example.tp_integrador.data.domain;

public class Relacion {
    private Integer idPerfilVoluntario;
    private Integer idProyecto;
    private Integer estadoVoluntario;
    private Integer estadoRelacion;

    public Relacion(Integer idPerfilVoluntario, Integer idProyecto, Integer estadoVoluntario, Integer estadoRelacion) {
        this.idPerfilVoluntario = idPerfilVoluntario;
        this.idProyecto = idProyecto;
        this.estadoVoluntario = estadoVoluntario;
        this.estadoRelacion = estadoRelacion;
    }

    public Relacion() {
    }

    public Integer getIdPerfilVoluntario() {
        return idPerfilVoluntario;
    }

    public void setIdPerfilVoluntario(Integer idPerfilVoluntario) {
        this.idPerfilVoluntario = idPerfilVoluntario;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getEstadoVoluntario() {
        return estadoVoluntario;
    }

    public void setEstadoVoluntario(Integer estadoVoluntario) {
        this.estadoVoluntario = estadoVoluntario;
    }

    public Integer getEstadoRelacion() {
        return estadoRelacion;
    }

    public void setEstadoRelacion(Integer estadoRelacion) {
        this.estadoRelacion = estadoRelacion;
    }
}
