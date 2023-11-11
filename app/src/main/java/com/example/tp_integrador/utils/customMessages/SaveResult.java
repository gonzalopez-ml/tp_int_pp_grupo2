package com.example.tp_integrador.utils.customMessages;

public class SaveResult {
    private boolean success;
    private String message;

    private long userId;

    public SaveResult(boolean success, String message, long userId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public long getUserId() {
        return userId;
    }
}