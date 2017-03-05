package com.company.recipes.dao;

import com.company.recipes.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDao extends CrudRepository<Recipe, Long>{
    @Query("select r from Recipe r")
    List<Recipe> findAll();

    Recipe findOne(@Param("id") Long id);
}
