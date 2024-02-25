package com.itvedant.petstoreapp.entities;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message =  "price cannot be null")
    @Min(value = 50, message = "price should be more than or equal to 50")
    @Max(value = 10000, message = "price should be less than or equal to 10000")
    private Double price;

    @NotNull(message = "description cannot be null")
    @Length(min = 50, max = 300, message = "description should be of 50 - 300")
    private String description;

    //@Past
    //@Future
    //@FutureorPresent
    @PastOrPresent
    private LocalDate manufacturingDate;
    
    
}
