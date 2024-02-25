package com.itvedant.petstoreapp.validators;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VerifyPasswordValidator 
        implements ConstraintValidator<VerifyPassword, Object>{

    private String field;
    private String matchingField;

    @Override
    public void initialize(VerifyPassword constraintAnnotation) {

        //We are initializing the names of the field that 
        //we want to check
        //field => password
        //matchingField => confirmPassword
        this.field = constraintAnnotation.field();
        this.matchingField = constraintAnnotation.matchingField();
    }

    @Override
    public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
        //But we want the value of these two fields

        Object fieldValue = new BeanWrapperImpl(arg0)
                                    .getPropertyValue(field);
        Object matchingFieldValue = new BeanWrapperImpl(arg0)
                                    .getPropertyValue(matchingField);
return fieldValue.equals(matchingFieldValue);
    }    
}
        



