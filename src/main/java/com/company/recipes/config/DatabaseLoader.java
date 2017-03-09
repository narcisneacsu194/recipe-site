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
                13, 15, "http://placehold.it/350x150");
        Ingredient ingredient = new Ingredient("Item 1", "State 1", 23);
        Ingredient ingredient2 = new Ingredient("Item 2", "State 2", 33);
        Ingredient ingredient3 = new Ingredient("Item 3", "State 3", 44);
        Ingredient ingredient4 = new Ingredient("Item 4", "State 4", 55);
        Ingredient ingredient5 = new Ingredient("Item 5", "State 5", 66);
        Step step = new Step("Step 1");
        Step step2 = new Step("Step 2");
        Step step3 = new Step("Step 3");
        Step step4 = new Step("Step 4");
        Step step5 = new Step("Step 5");

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        recipe.addIngredient(ingredient4);
        recipe.addIngredient(ingredient5);
        recipe.addStep(step);
        recipe.addStep(step2);
        recipe.addStep(step3);
        recipe.addStep(step4);
        recipe.addStep(step5);
        ingredient.setRecipe(recipe);
        ingredient2.setRecipe(recipe);
        ingredient3.setRecipe(recipe);
        ingredient4.setRecipe(recipe);
        ingredient5.setRecipe(recipe);
        step.setRecipe(recipe);
        step2.setRecipe(recipe);
        step3.setRecipe(recipe);
        step4.setRecipe(recipe);
        step5.setRecipe(recipe);
        recipeDao.save(recipe);


        Recipe recipe2 = new Recipe("Name 2", "Description 2", Recipe.Category.BREAKFAST,
                13, 15, "http://placehold.it/350x150");
        Recipe recipe3 = new Recipe("Name 3", "Description 3", Recipe.Category.BREAKFAST,
                13, 15, "http://placehold.it/350x150");
        Recipe recipe4 = new Recipe("Name 4", "Description 4", Recipe.Category.BREAKFAST,
                13, 15, "http://placehold.it/350x150");
        Recipe recipe5 = new Recipe("Name 5", "Description 5", Recipe.Category.BREAKFAST,
                13, 15, "http://placehold.it/350x150");
        recipeDao.save(recipe2);
        recipeDao.save(recipe3);
        recipeDao.save(recipe4);
        recipeDao.save(recipe5);
    }
}
