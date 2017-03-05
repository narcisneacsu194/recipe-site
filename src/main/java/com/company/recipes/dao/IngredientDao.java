package com.company.recipes.dao;

import com.company.recipes.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Long>{
    @Query("select i from Ingredient i")
    List<Ingredient> findAll();
}
