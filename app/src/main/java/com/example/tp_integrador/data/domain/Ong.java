package com.example.tp_integrador.data.domain;

import javax.annotation.Nullable;

public class Ong {
    @Nullable
    private Integer idOng;
    private String name;
    private String description;
    private String logo;
    private String phone;
    private String mail;
    private String location;

    private Usuario usuario;

    public Ong() {
    }

    public Ong(@Nullable Integer idOng, String name, String description, String logo, String phone, String mail, String location) {
        this.idOng = idOng;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.phone = phone;
        this.mail = mail;
        this.location = location;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Nullable
    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(@Nullable Integer idOng) {
        this.idOng = idOng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
