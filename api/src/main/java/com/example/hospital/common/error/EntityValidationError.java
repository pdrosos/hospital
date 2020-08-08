package com.example.hospital.common.error;

public class EntityValidationError {

    private final String code;
    private final String field;
    private final String defaultMessage;

    public EntityValidationError(String code, String field, String defaultMessage) {
        this.code = code;
        this.field = field;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

}
