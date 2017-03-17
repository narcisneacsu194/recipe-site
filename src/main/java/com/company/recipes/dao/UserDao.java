package com.company.recipes.dao;

import com.company.recipes.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserDao extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
