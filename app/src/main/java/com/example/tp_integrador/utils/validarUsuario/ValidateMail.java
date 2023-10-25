package com.example.tp_integrador.utils.validarUsuario;

public class ValidateMail implements IValidateMail {
    @Override
    public Boolean validate(String user, String password) {
        if (user.contains("@") && user.contains(".com")) {
            return !password.isEmpty() && password.length() > 8;
        }
        return false;
    }
}
