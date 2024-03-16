package com.itvedant.petstoreapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itvedant.petstoreapp.entities.Address;

public interface AddressRepository extends
    CrudRepository<Address, Integer>{

}
