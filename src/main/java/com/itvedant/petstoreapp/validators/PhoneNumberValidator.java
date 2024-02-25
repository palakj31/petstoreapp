package com.itvedant.petstoreapp.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator
        implements ConstraintValidator <PhoneNumber, String>{

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1){
        boolean condition = arg0.length() == 10 && arg0.matches("[0-9]+");

        return condition;
    }
    
}
