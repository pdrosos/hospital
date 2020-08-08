package com.example.hospital.common.error;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final Collection<String> errors;

    public ApiErrorResponse(HttpStatus status, String message, Collection<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorResponse(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public Instant getTimestamp () {
        return Instant.now();
    }

    public int getStatusCode() {
        return status.value();
    }

    public String getStatusReason() {
        return status.getReasonPhrase();
    }

    public String getMessage() {
        return message;
    }

    public Collection<String> getErrors() {
        return errors;
    }

}
