package com.example.hospital.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class FirstNumberLessThanSecondNumberValidator implements ConstraintValidator<FirstNumberLessThanSecondNumber, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FirstNumberLessThanSecondNumber constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Integer firstValue = (Integer) wrapper.getPropertyValue(firstFieldName);
        Integer secondValue = (Integer) wrapper.getPropertyValue(secondFieldName);

        boolean valid = firstValue != null && secondValue != null && firstValue < secondValue;

        if (!valid) {
            context.
                    buildConstraintViolationWithTemplate(message).
                    addPropertyNode(firstFieldName).
                    addConstraintViolation().
                    buildConstraintViolationWithTemplate(message).
                    addPropertyNode(secondFieldName).
                    addConstraintViolation().
                    disableDefaultConstraintViolation();
        }

        return valid;
    }
}
