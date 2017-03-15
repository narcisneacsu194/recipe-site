package com.company.recipes.services;

import com.company.recipes.dao.RecipeDao;
import com.company.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private RecipeDao recipeDao;

    @Override
    @SuppressWarnings("unchecked")
    public List<Recipe> findAll() {
        return (List)recipeDao.findAll();
    }

    @Override
    public Recipe findOne(Long id) {
        return recipeDao.findOne(id);
    }

    @Override
    public void save(Recipe recipe) {
        recipeDao.save(recipe);
    }

    @Override
    public void save(List<Recipe> recipes){
        recipeDao.save(recipes);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeDao.delete(recipe);
    }

    @Override
    public List<Recipe> findByDescriptionContaining(String descriptionChunk){
        return recipeDao.findByDescriptionContaining(descriptionChunk);
    }

    @Override
    public List<Recipe> findByCategory(String categoryName) {
        return recipeDao.findByCategory(Recipe.Category.valueOf(categoryName.toUpperCase()));
    }
}
