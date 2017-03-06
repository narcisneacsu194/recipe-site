package com.company.recipes.config;

import com.company.recipes.dao.IngredientDao;
import com.company.recipes.dao.RecipeDao;
import com.company.recipes.dao.StepDao;
import com.company.recipes.model.Ingredient;
import com.company.recipes.model.Recipe;
import com.company.recipes.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner{

    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;
    private StepDao stepDao;

    @Autowired
    public DatabaseLoader(RecipeDao recipeDao, IngredientDao ingredientDao,
                          StepDao stepDao){
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
        this.stepDao = stepDao;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Recipe recipe = new Recipe("Name 1", "Description 1", Recipe.Category.BREAKFAST,
                13, 15);
        Ingredient ingredient = new Ingredient("Item 1", "State 1", 23);
        Step step = new Step("Step 1");
        recipe.addIngredient(ingredient);
        recipe.addStep(step);
        recipeDao.save(recipe);
        Recipe recipe2 = new Recipe("Name 2", "Description 2", Recipe.Category.BREAKFAST,
                13, 15);
        Recipe recipe3 = new Recipe("Name 3", "Description 3", Recipe.Category.BREAKFAST,
                13, 15);
        Recipe recipe4 = new Recipe("Name 4", "Description 4", Recipe.Category.BREAKFAST,
                13, 15);
        Recipe recipe5 = new Recipe("Name 5", "Description 5", Recipe.Category.BREAKFAST,
                13, 15);
        recipeDao.save(recipe2);
        recipeDao.save(recipe3);
        recipeDao.save(recipe4);
        recipeDao.save(recipe5);
    }
}
