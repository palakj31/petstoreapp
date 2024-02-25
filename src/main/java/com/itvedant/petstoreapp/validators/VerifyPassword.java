package com.itvedant.petstoreapp.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyPasswordValidator.class)
public @interface VerifyPassword {
    String field();

    String matchingField();

    String message() default "Password and Confirm Password does not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
