package com.company.recipes.services;

import com.company.recipes.dao.RecipeDao;
import com.company.recipes.enums.Category;
import com.company.recipes.model.Recipe;
import org.hibernate.Hibernate;
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
        return recipeDao.findAll();
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
        List<Recipe> recipes = recipeDao.findByDescriptionContaining(descriptionChunk);
        for(Recipe recipe : recipes){
            Hibernate.initialize(recipe.getFavoriteUsers());
        }
        return recipes;
    }

    @Override
    public List<Recipe> findByCategory(String categoryName) {
        List<Recipe> recipes = recipeDao.findByCategory(Category.valueOf(categoryName.toUpperCase()));
        for(Recipe recipe : recipes){
            Hibernate.initialize(recipe.getFavoriteUsers());
        }
        return recipes;
    }

    @Override
    public List<Recipe> findAllFromSpecificUser() {
        return recipeDao.findAllFromSpecificUser();
    }
}
