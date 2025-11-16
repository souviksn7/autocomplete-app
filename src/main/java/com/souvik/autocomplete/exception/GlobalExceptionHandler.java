package com.souvik.autocomplete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Map;

/**
 * Global exception handler for the entire application.
 * Catches unhandled exceptions and returns a structured JSON error response
 * instead of exposing internal stack traces to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Utility method to construct a standardized error response object.
     *
     * @param status HTTP status to return
     * @param msg    error message to include in the response
     */
    private ResponseEntity<ErrorDetails> build(HttpStatus status, String msg) {
        return new ResponseEntity<>(
                new ErrorDetails(status.value(), status.getReasonPhrase(), msg),
                status
        );
    }

    /**
     * Handles cases where a required request parameter is missing.
     * Returns 400 Bad Request.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDetails> handleMissingParam(MissingServletRequestParameterException ex) {
        return build(HttpStatus.BAD_REQUEST, "Missing required parameter: " + ex.getParameterName());
    }

    /**
     * Handles any unexpected/unhandled server-side exceptions.
     * Returns a 500 Internal Server Error with the exception message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneralError(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
