package com.example.tp_integrador.data.domain;

public class Usuario {
    private Integer idUser;
    private String mail;
    private String password;
    private TipoUser tipoUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public Usuario(String mail, String password, TipoUser tipoUser) {
        this.mail = mail;
        this.password = password;
        this.tipoUser = tipoUser;
    }

    public Usuario() {
    }
}
