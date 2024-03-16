package com.itvedant.petstoreapp.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Projection(name = "userprojection1", types = {User.class})
public interface UserProjection {
    
    //in this interface we will add the getter methods
    //of the field that we want to be included in
    //this projection of the User entity

    //we will add getter methods as projection is used
    //for only reading the data

    //This projection is called closed projection
    //In Closed projection the fields of the entity
    //are projection as it is without any modification

    Integer getId();
    //String getFirstName();
    //String getLastName();
    String getEmail();

    //In Open projection the fields to be displayed
    //in the projection are created using @Value 
    //annotation
    //here target is referring User entity

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}
