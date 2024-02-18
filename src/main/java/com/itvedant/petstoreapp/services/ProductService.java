package com.itvedant.petstoreapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itvedant.petstoreapp.entities.Product;

//@service will work same as that of @component annotation
@Service
public class ProductService {
    Map<Integer, Product> products = new HashMap<>();

    public String addProduct(){
        Product p1 = new Product();
        p1.setId(10);
        p1.setName("dog shoes - red");
        p1.setPrice(670.00);
        p1.setDescription("beautiful red shoes for pet dogs");
        products.put(p1.getId(),p1);
    
        Product p2 = new Product();
        p2.setId(20);
        p2.setName("dog shoes - green");
        p2.setPrice(670.00);
        p2.setDescription("beautiful green shoes for pet dogs");
        products.put(p2.getId(),p2);
            
        return "Product Added";
    }

    public List<Product> getAll(){
        return new ArrayList<Product>(products.values());
    }

    public String deleteProduct(Integer id){
        products.remove(id);
        return "Product Deleted";
    }
        
    }

