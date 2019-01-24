package com.company.recipes.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientModelTest {

    private Ingredient ingredient;

    @Before
    public void setUp(){
        ingredient = new Ingredient();
    }

    @Test
    public void setDescription_ShouldSetDescriptionForIngredient(){
        ingredient.setDescription("Description 1");
        Assert.assertEquals("Description 1", ingredient.getDescription());
    }

    @Test
    public void setError_ShouldSetErrorForIngredient(){
        ingredient.setError("Error 1");
        Assert.assertEquals("Error 1", ingredient.getError());
    }

    @Test
    public void setRecipe_ShouldSetRecipeForIngredient(){
        Recipe recipe = new Recipe();
        ingredient.setRecipe(recipe);

        Assert.assertEquals(recipe, ingredient.getRecipe());
    }
}
