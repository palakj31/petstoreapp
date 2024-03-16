package com.itvedant.petstoreapp.entities;

import java.time.Instant;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itvedant.petstoreapp.validators.PhoneNumber;
import com.itvedant.petstoreapp.validators.VerifyPassword;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;
    
    //@JoinColumn will create a foriegn key column
    //in the user table that refers the id column 
    //of the address table
    @OneToOne
    @JoinColumn(name = "addr")
    private Address addr;
    
}
