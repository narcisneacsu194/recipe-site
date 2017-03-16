package com.company.recipes.services;

import com.company.recipes.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    List<Recipe> findByDescriptionContaining(String descriptionChunk);
    List<Recipe> findByCategory(String categoryName);
    List<Recipe> findAllFromSpecificUser();
    Recipe findOne(Long id);
    void save(Recipe recipe);
    void save(List<Recipe> recipes);
    void delete(Recipe recipe);
}
