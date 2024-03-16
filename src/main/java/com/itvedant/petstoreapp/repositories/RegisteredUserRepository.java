package com.itvedant.petstoreapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.petstoreapp.entities.RegisteredUser;

public interface RegisteredUserRepository 
    extends JpaRepository<RegisteredUser, Integer>{ 
    
    RegisteredUser findByEmail(String email);
}
