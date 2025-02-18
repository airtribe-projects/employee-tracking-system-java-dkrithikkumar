package com.airtribe.EmployeeTrackingSystem.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullableNotBlankDateFormatValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullableNotBlankDateFormat {
    String message() default "Must be null or a non-blank string and must follow YYYY-MM-DD format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
