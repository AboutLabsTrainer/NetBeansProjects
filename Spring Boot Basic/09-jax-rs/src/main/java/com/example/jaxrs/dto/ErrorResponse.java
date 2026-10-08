package com.example.jaxrs.dto;

/**
 * JSON-Körper, den der JAX-RS-ExceptionMapper bei einem Fehler zurückgibt.
 * Er ist ein Übertragungsobjekt und keine Exception.
 */
public class ErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
