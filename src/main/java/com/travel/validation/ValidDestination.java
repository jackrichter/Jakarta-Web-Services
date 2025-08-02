package com.travel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {ValidDestinationCheck.class})
public @interface ValidDestination {
    String message() default "Destination is not valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
