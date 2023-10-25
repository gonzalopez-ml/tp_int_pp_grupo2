package com.example.tp_integrador.utils.customMessages;

public class SaveResult {
    private boolean success;
    private String message;

    public SaveResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}