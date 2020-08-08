package com.example.hospital.common.web;

import com.example.hospital.common.error.ApiErrorResponse;

import com.example.hospital.common.error.EntityValidationError;
import com.example.hospital.common.error.EntityValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Response {

    public static <T> ResponseEntity<? extends Object> of(Optional<T> entity, String notFoundMessage) {
        Assert.notNull(entity, "Entity must not be null");

        if (entity.isEmpty()) {
            return notFound(notFoundMessage);
        }

        return ResponseEntity.ok(entity.get());
    }

    public static <T> ResponseEntity<? extends Object> of(Optional<T> entity, Object id) {
        String notFoundMessage = MessageFormat.format("Entity with id {0} not found", id.toString());

        return of (entity, notFoundMessage);
    }

    public static ResponseEntity<ApiErrorResponse> notFound(String message) {
        var body = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                message,
                message
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    public static ResponseEntity<ApiErrorResponse> notFound(Object id) {
        String message = MessageFormat.format("Entity with id {0} not found", id.toString());

        return notFound(message);
    }

    public static ResponseEntity<EntityValidationErrorResponse> unprocessableEntity(Collection<FieldError> fieldErrors, String message) {
        List<EntityValidationError> errors = fieldErrors.stream().
                map(e -> new EntityValidationError(e.getCode(), e.getField(), e.getDefaultMessage())).
                collect(Collectors.toList());

        var body = new EntityValidationErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                message,
                errors
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }

    public static ResponseEntity<EntityValidationErrorResponse> unprocessableEntity(Collection<FieldError> fieldErrors) {
        String message = "Validation failed";

        return unprocessableEntity(fieldErrors, message);
    }

    public static <T> ResponseEntity<T> created(T entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

}
