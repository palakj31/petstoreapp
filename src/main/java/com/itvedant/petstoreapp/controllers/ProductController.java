package com.itvedant.petstoreapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.petstoreapp.entities.Product;
import com.itvedant.petstoreapp.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public String addProduct(){
        return this.service.addProduct();
    }
    
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return this.service.getAll();
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable Integer id){
        return this.service.deleteProduct(id);
    }
}
