package com.itvedant.petstoreapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.petstoreapp.entities.User;
import com.itvedant.petstoreapp.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
 
    public User addUser(User newUser){
        return this.userRepository.save(newUser);
    }

    public Iterable<User> getAll(){
        return this.userRepository.findAll();
    }

    public User getUser(Integer id){
        return this.userRepository.findById(id).orElse(null);
    }

    public String deleteUser(Integer id){
        this.userRepository.deleteById(id);
        return "User Deleted";
    }
    public User updateUser(Integer id, User updateUser){
        updateUser.setId(id);
        return this.userRepository.save(updateUser);
    }

}
