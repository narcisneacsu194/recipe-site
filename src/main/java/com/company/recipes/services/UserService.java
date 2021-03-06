package com.company.recipes.services;

import com.company.recipes.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);
    User registerNewUser(String username, boolean enabled, String password, String matchingPassword);
    void save(User user);
    List<User> findAll();
}
