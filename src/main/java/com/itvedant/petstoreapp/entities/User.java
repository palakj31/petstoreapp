package com.itvedant.petstoreapp.entities;

import org.hibernate.validator.constraints.Length;

import com.itvedant.petstoreapp.validators.PhoneNumber;
import com.itvedant.petstoreapp.validators.VerifyPassword;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@VerifyPassword(field = "password", matchingField = "confirmPassword")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Length(min = 3)
    private String firstName;

    @NotNull
    @Length(min = 4)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @PhoneNumber
    private String phone;

    private String password;
    private String confirmPassword;
    
}
