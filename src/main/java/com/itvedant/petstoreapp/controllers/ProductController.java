package com.itvedant.petstoreapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.petstoreapp.entities.Product;
import com.itvedant.petstoreapp.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product newProduct){
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.service.addProduct(newProduct));
    }
    
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(this.service.getAll());
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        Product findProduct = this.service.getProduct(id);
        if(findProduct == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with this id does not exists");
        else
            return ResponseEntity.ok(this.service.deleteProduct(id));
    }
    @RequestMapping(path= "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        Product findProduct = this.service.getProduct(id);
        if(findProduct == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with this id does not exists");
        else
            return ResponseEntity.ok(findProduct);

    }
    @RequestMapping(path = "/product/{id}",method = RequestMethod.PUT )
     public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product prod){
            Product findProduct = this.service.getProduct(id);
            if(findProduct == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with this id does not exists");
            else
                return ResponseEntity.ok(this.service.updateProduct(id, prod));
    }
}

