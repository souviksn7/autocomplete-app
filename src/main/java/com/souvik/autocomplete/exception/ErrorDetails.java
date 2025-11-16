package com.souvik.autocomplete.exception;

import java.time.Instant;

public class ErrorDetails {
    private String timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorDetails(int status, String error, String message) {
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
}
