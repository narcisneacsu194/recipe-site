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

import java.util.stream.IntStream;

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

        IntStream.range(1, 100).forEach(value -> {
            Recipe recipe = new Recipe("Name " + value, "Description " + value, Recipe.Category.BREAKFAST,
                    13, 15, "http://placehold.it/350x150");

            IntStream.range(1, 5).forEach(value2 -> {
                Ingredient ingredient = new Ingredient("Item " + value2, "State " + value2, 23);
                Step step = new Step("Step" + value2);

                recipe.addIngredient(ingredient);
                recipe.addStep(step);
                ingredient.setRecipe(recipe);
                step.setRecipe(recipe);
                recipeDao.save(recipe);
            });


        });

    }
}
