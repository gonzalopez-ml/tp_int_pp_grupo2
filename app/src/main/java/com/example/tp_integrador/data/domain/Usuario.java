package com.example.tp_integrador.data.domain;

public class Usuario {
    private Integer idUser;
    private String name;
    private String apellido;
    private String mail;
    private String password;
    private TipoUser tipoUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUser getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(TipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    public Usuario(String name, String apellido, String mail, String password, TipoUser tipoUser) {
        this.name = name;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.tipoUser = tipoUser;
    }

    public Usuario() {
    }
}
