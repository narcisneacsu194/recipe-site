package com.company.recipes.dao;

import com.company.recipes.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long>{
    User findByUsername(String username);

//    @Query("select u from User u join fetch u.favo")
//    User findByIdAndFetchRecipesEagerly(@Param("id") Long id);

}
