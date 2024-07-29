package com.itvedant.petstoreapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.itvedant.petstoreapp.entities.Product;

import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false" )
public interface ProductRepository 
    extends JpaRepository<Product, Integer>{
    //this method will create a select query
    //in which a where clause will be added to 
    //create the condition on name column
    @PreAuthorize("hasRole('HR')")
    List<Product> findByName(String name);

    @RolesAllowed("IT")
    @RestResource(path="pricegreater")
    List<Product> findByPriceGreaterThan(Double value);
    
    @Secured("ROLE_HR")
    @RestResource(path="priceless")
    List<Product> findByPriceLessThan(Double value);
    
}
