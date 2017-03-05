package com.company.recipes.dao;

import com.company.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDao extends CrudRepository<Recipe, Long>{
}
