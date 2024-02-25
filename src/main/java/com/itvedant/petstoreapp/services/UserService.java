package com.itvedant.petstoreapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;



import org.springframework.stereotype.Service;

import com.itvedant.petstoreapp.entities.User;

@Service
public class UserService {
    private Map<Integer, User> users = new HashMap<>();

    private AtomicInteger atomic = new AtomicInteger(0);
    
 
    public User addUser(User newUser){
        newUser.setId(atomic.incrementAndGet());
        users.put(newUser.getId(), newUser);
        return newUser;
    }

    public List<User> getAll(){
        return new ArrayList<User>(users.values());
    }

    public User getUser(Integer id){
        return users.get(id);
    }

    public String deleteUser(Integer id){
        users.remove(id);
        return "User Deleted";
    }
    public User updateUser(Integer id, User updateUser){
        updateUser.setId(id);
        users.put(id, updateUser);
        return updateUser;
    }

}
