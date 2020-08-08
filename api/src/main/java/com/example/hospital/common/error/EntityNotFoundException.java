package com.example.hospital.common.error;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity not found");
    }

    public EntityNotFoundException(Object id) {
        super(MessageFormat.format("Entity with id {0} not found", id.toString()));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

}