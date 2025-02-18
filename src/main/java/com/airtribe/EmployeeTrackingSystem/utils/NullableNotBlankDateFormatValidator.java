package com.airtribe.EmployeeTrackingSystem.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class NullableNotBlankDateFormatValidator implements ConstraintValidator<NullableNotBlankDateFormat, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        value = value.trim();

        if (value.isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(value, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid Date Format. Use YYYY-MM-DD")
                    .addConstraintViolation();
            return false;
        }
    }
}
