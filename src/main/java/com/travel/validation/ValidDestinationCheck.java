package com.travel.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class ValidDestinationCheck implements ConstraintValidator<ValidDestination, String> {

    private List<String> validDestinations = new ArrayList<>(List.of("LGB","LAX","OAK","ONT",
            "SNA","SAN","SFO","SJC", "SCK","SBA"));

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validDestinations.contains(value);
    }
}
