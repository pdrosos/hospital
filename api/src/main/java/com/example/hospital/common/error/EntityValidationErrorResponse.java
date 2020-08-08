package com.example.hospital.common.error;

import java.time.Instant;
import java.util.Collection;

import org.springframework.http.HttpStatus;

public class EntityValidationErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final Collection<EntityValidationError> errors;

    public EntityValidationErrorResponse(HttpStatus status, String message, Collection<EntityValidationError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
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

    public Collection<EntityValidationError> getErrors() {
        return errors;
    }

}
