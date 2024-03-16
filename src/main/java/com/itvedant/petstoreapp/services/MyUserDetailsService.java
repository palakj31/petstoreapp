package com.itvedant.petstoreapp.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itvedant.petstoreapp.entities.RegisteredUser;
import com.itvedant.petstoreapp.repositories.RegisteredUserRepository;

import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService
    implements UserDetailsService{

    @Autowired
    private RegisteredUserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException {
        RegisteredUser foundUser = this.repository.findByEmail(username);

        if(foundUser == null)
            throw new UsernameNotFoundException("User does not exists");
        else{
            Collection<GrantedAuthority> authorties = new ArrayList<>();
            for(String role: foundUser.getRoles()){
                authorties.add(new SimpleGrantedAuthority(role));
            }

            RegisteredUser user = new RegisteredUser();
            user.setEmail(foundUser.getEmail());
            user.setPassword(foundUser.getPassword());
            user.setAuthorities(authorties); //authorties are used for roles
            return user;
        }
    }
}
    

