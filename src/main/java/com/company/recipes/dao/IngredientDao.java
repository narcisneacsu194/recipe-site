package com.company.recipes.dao;

import com.company.recipes.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Long>{
}
