package com.example.tp_integrador.data.domain;

import java.io.Serializable;

public class TipoUser implements Serializable {
    int id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoUser(int id) {
        this.id = id;
    }

    public TipoUser() {
    }
}
