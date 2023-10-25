package com.example.tp_integrador.utils.validarCamposVacios;

import java.util.List;

import javax.inject.Inject;

public class ValidateInputs implements IValidateInputs {

    @Inject
    public ValidateInputs() {
    }

    @Override
    public Boolean apply(List<String> lista) {
        return lista.stream().allMatch(s -> !s.isEmpty());
    }
}
