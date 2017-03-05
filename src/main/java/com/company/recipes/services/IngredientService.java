package com.company.recipes.services;

import com.company.recipes.model.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient findOne(Long id);
    void save(Ingredient ingredient);
    void save(List<Ingredient> ingredients);
    void delete(Ingredient ingredient);
}
