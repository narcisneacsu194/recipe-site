package com.company.recipes.services;

import com.company.recipes.dao.UserDao;
import com.company.recipes.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ComponentScan
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        Hibernate.initialize(user.getFavoritedRecipes());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException{
        User user = userDao.findByUsername(username);
        if(user ==  null){
            throw new UsernameNotFoundException(
              username + " was not found"
            );
        }

        return user;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User registerNewUser(String username, boolean enabled, String password, String matchingPassword) {
        return userDao.save(new User(username, enabled, password, matchingPassword));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
