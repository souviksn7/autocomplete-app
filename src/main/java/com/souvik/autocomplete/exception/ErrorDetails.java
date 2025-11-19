package com.souvik.autocomplete.exception;

import java.time.Instant;

/**
 * Represents a structured error response returned by the GlobalExceptionHandler.
 * This ensures all errors follow a consistent JSON format.
 */
public class ErrorDetails {
    // ISO timestamp when the error occurred
    private String timestamp;

    // HTTP status code
    private int status;

    // Short HTTP error description
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
