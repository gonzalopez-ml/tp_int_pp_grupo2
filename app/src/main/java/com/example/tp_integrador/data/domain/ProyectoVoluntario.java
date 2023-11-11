package com.example.tp_integrador.data.domain;

public class ProyectoVoluntario {
    private Proyecto proyecto;
    private Voluntario voluntario;

    public ProyectoVoluntario(Proyecto proyecto, Voluntario voluntario) {
        this.proyecto = proyecto;
        this.voluntario = voluntario;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }
}