package com.company.recipes.utilities;

import com.company.recipes.model.Ingredient;
import com.company.recipes.model.Recipe;
import com.company.recipes.model.Step;
import com.company.recipes.pojo.IngredientPojo;
import com.company.recipes.pojo.RecipePojo;
import com.company.recipes.pojo.StepPojo;

import java.util.ArrayList;
import java.util.List;

public class UtilityMethods {
    public static  List<Recipe> nullAndNonNullUserFavoriteRecipeList(List<Recipe> recipes, List<Recipe> favorites){
        List<Recipe> nullAndNonNullUserFavoriteRecipeList = new ArrayList<>();
        recipes.forEach(recipe -> {
            if (favorites.contains(recipe)) {
                nullAndNonNullUserFavoriteRecipeList.add(recipe);
            } else {
                nullAndNonNullUserFavoriteRecipeList.add(null);
            }
        });

        return nullAndNonNullUserFavoriteRecipeList;
    }

    private static RecipePojo recipeModelToPojo(Recipe recipe){
        RecipePojo recipePojo = new RecipePojo();

        recipePojo.setAuthor(recipe.getUser().getUsername());
        recipePojo.setName(recipe.getName());
        recipePojo.setDescription(recipe.getDescription());
        recipePojo.setCategory(recipe.getCategory());
        recipePojo.setPrepTimeHour(recipe.getPrepTimeHour());
        recipePojo.setPrepTimeMinute(recipe.getPrepTimeMinute());
        recipePojo.setCookTimeHour(recipe.getCookTimeHour());
        recipePojo.setCookTimeMinute(recipe.getCookTimeMinute());
        recipePojo.setPhotoUrl(recipe.getPhotoUrl());

        List<Ingredient> ingredients = recipe.getIngredients();
        List<IngredientPojo> ingredientPojos = new ArrayList<>();
        ingredients.forEach(ingredient -> {
            IngredientPojo ingredientPojo = new IngredientPojo();
            ingredientPojo.setDescription(ingredient.getDescription());
            ingredientPojos.add(ingredientPojo);
        });

        List<Step> steps = recipe.getSteps();
        List<StepPojo> stepPojos = new ArrayList<>();
        steps.forEach(step -> {
            StepPojo stepPojo = new StepPojo();
            stepPojo.setDescription(step.getDescription());
            stepPojos.add(stepPojo);
        });

        recipePojo.setIngredients(ingredientPojos);
        recipePojo.setSteps(stepPojos);

        return recipePojo;
    }

    public static List<RecipePojo> recipeModelListToPojoList(List<Recipe> recipeList){
        List<RecipePojo> recipePojoList = new ArrayList<>();

        for(Recipe recipe : recipeList){
            RecipePojo recipePojo = recipeModelToPojo(recipe);

            recipePojoList.add(recipePojo);
        }

        return recipePojoList;
    }
}
