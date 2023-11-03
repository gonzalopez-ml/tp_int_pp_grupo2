package com.example.tp_integrador.data.domain;

public class Voluntario {
    private Integer idVoluntario;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String skills;
    private String availability;
    private String cv;
    private String photo;

    private Usuario usuario;

    public Voluntario() {
    }

    public Voluntario(Integer idVoluntario, String name, String lastName, String dni, String phone, String skills, String availability, String cv, String photo) {
        this.idVoluntario = idVoluntario;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
        this.skills = skills;
        this.availability = availability;
        this.cv = cv;
        this.photo = photo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Integer idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
