package com.company.recipes.services;

import com.company.recipes.dao.IngredientDao;
import com.company.recipes.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientDao ingredientDao;

    @Override
    @SuppressWarnings("unchecked")
    public List<Ingredient> findAll() {
        return (List)ingredientDao.findAll();
    }

    @Override
    public Ingredient findOne(Long id) {
        return ingredientDao.findOne(id);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientDao.save(ingredient);
    }

    @Override
    public void save(List<Ingredient> ingredients) {
        ingredientDao.save(ingredients);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientDao.delete(ingredient);
    }
}
