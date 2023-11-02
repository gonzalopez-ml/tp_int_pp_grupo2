package com.example.tp_integrador.data.domain.enums;

public enum RequestType {
    PDF(1),
    PHOTO(2);

    private final int value;

    RequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
