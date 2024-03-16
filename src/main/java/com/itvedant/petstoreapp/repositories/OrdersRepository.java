package com.itvedant.petstoreapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itvedant.petstoreapp.entities.Orders;

public interface OrdersRepository 
    extends CrudRepository<Orders, Integer>{
    
}
