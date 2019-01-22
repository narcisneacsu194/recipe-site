package com.company.recipes.model;

import com.company.recipes.enums.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RecipeModelTest {

    private Recipe recipe;

    @Before
    public void setUp(){
        recipe = new Recipe();
    }

    @Test
    public void setName_ShouldSetNameForRecipe(){
        recipe.setName("Name 1");
        Assert.assertEquals("Name 1", recipe.getName());
    }

    @Test
    public void setDescription_ShouldSetDescriptionForRecipe(){
        recipe.setDescription("Description 1");
        Assert.assertEquals("Description 1", recipe.getDescription());
    }

    @Test
    public void setCategory_ShouldSetCategoryForRecipe(){
        recipe.setCategory(Category.BREAKFAST);
        Assert.assertEquals(Category.BREAKFAST, recipe.getCategory());
    }

    @Test
    public void setPrepTimeHour_ShouldGetPrepTimeHourForRecipe(){
        recipe.setPrepTimeHour(1);
        Assert.assertEquals(new Integer(1), recipe.getPrepTimeHour());
    }

    @Test
    public void setPrepTimeMinute_ShouldGetPrepTimeMinuteForRecipe(){
        recipe.setPrepTimeMinute(1);
        Assert.assertEquals(new Integer(1), recipe.getPrepTimeMinute());
    }

    @Test
    public void setCookTimeHour_ShouldGetCookTimeHourForRecipe(){
        recipe.setCookTimeHour(1);
        Assert.assertEquals(new Integer(1), recipe.getCookTimeHour());
    }

    @Test
    public void setCookTimeMinute_ShouldGetCookTimeMinuteForRecipe(){
        recipe.setCookTimeMinute(1);
        Assert.assertEquals(new Integer(1), recipe.getCookTimeMinute());
    }

    @Test
    public void setPhotoUrl_ShouldSetPhotoUrlForRecipe(){
        recipe.setPhotoUrl("url");
        Assert.assertEquals("url", recipe.getPhotoUrl());
    }

    @Test
    public void setIngredients_ShouldSetIngredientsForRecipe(){
        List<Ingredient> ingredientList;
        Ingredient ingredient1 = new Ingredient("NewIngredient1");
        Ingredient ingredient2 = new Ingredient("NewIngredient2");
        ingredientList = Arrays.asList(ingredient1, ingredient2);
        recipe.setIngredients(ingredientList);
        List<Ingredient> returnedIngredients = recipe.getIngredients();

        Assert.assertEquals(ingredientList, returnedIngredients);
        Assert.assertEquals(ingredient1,
                returnedIngredients.get(0));
        Assert.assertEquals(ingredient2,
                returnedIngredients.get(1));
    }

    @Test
    public void addIngredient_ShouldAddIngredientToIngredientList(){
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient("NewIngredient1");
        Ingredient ingredient2 = new Ingredient("NewIngredient2");
        ingredientList.add(ingredient1);
        recipe.setIngredients(ingredientList);
        recipe.addIngredient(ingredient2);
        List<Ingredient> returnedIngredients = recipe.getIngredients();

        Assert.assertEquals(ingredientList, returnedIngredients);
        Assert.assertEquals(ingredient1, returnedIngredients.get(0));
        Assert.assertEquals(ingredient2, returnedIngredients.get(1));
    }

    @Test
    public void setSteps_ShouldSetStepsForRecipe(){
        List<Step> stepList;
        Step step1 = new Step("NewStep1");
        Step step2 = new Step("NewStep2");
        stepList = Arrays.asList(step1, step2);
        recipe.setSteps(stepList);
        List<Step> returnedSteps = recipe.getSteps();

        Assert.assertEquals(stepList, returnedSteps);
        Assert.assertEquals(step1, returnedSteps.get(0));
        Assert.assertEquals(step2, returnedSteps.get(1));
    }

    @Test
    public void addStep_ShouldAddStepToStepList(){
        List<Step> stepList = new ArrayList<>();
        Step step1 = new Step("NewStep1");
        Step step2 = new Step("NewStep2");
        stepList.add(step1);
        recipe.setSteps(stepList);
        recipe.addStep(step2);
        List<Step> returnedSteps = recipe.getSteps();

        Assert.assertEquals(stepList, returnedSteps);
        Assert.assertEquals(step1, returnedSteps.get(0));
        Assert.assertEquals(step2, returnedSteps.get(1));
    }

    @Test
    public void setFavoriteUsers_ShouldSetFavoriteUsersForRecipe(){
        List<User> favoriteUsers;
        User user1 = new User();
        User user2 = new User();
        favoriteUsers = Arrays.asList(user1, user2);
        recipe.setFavoriteUsers(favoriteUsers);
        List<User> returnedUsers = recipe.getFavoriteUsers();

        Assert.assertEquals(favoriteUsers, returnedUsers);
        Assert.assertEquals(user1, returnedUsers.get(0));
        Assert.assertEquals(user2, returnedUsers.get(1));
    }

    @Test
    public void addFavoriteUser_ShouldAddUserToFavoriteUserList(){
        List<User> favoriteUsers = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();

        favoriteUsers.add(user1);
        recipe.setFavoriteUsers(favoriteUsers);
        recipe.addFavoriteUser(user2);
        List<User> returnedUsers = recipe.getFavoriteUsers();

        Assert.assertEquals(favoriteUsers, returnedUsers);
        Assert.assertEquals(user1, returnedUsers.get(0));
        Assert.assertEquals(user2, returnedUsers.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeFavoriteUser_ShouldAddUserToFavoriteUserList(){
        List<User> favoriteUsers;
        User user1 = new User();
        User user2 = new User();
        favoriteUsers = new LinkedList<>(Arrays.asList(user1, user2));
        recipe.setFavoriteUsers(favoriteUsers);
        recipe.removeFavoriteUser(user2);
        List<User> returnedUsers = recipe.getFavoriteUsers();

        Assert.assertEquals(favoriteUsers, returnedUsers);
        Assert.assertEquals(user1, returnedUsers.get(0));
        returnedUsers.get(1);
    }

    @Test
    public void setUser_ShouldSetOwnerUser(){
        User user1 = new User();
        recipe.setUser(user1);

        Assert.assertEquals(user1, recipe.getUser());
    }

    @Test
    public void recipeConstructor_ShouldSetValueForAllInstanceVariables(){
        Recipe recipe = new Recipe("NewRecipe1", "Description1", Category.BREAKFAST,
                1, 2, 3, 4, "url");
        Assert.assertEquals("NewRecipe1", recipe.getName());
        Assert.assertEquals("Description1", recipe.getDescription());
        Assert.assertEquals(Category.BREAKFAST, recipe.getCategory());
        Assert.assertEquals(new Integer(1), recipe.getPrepTimeHour());
        Assert.assertEquals(new Integer(2), recipe.getPrepTimeMinute());
        Assert.assertEquals(new Integer(3), recipe.getCookTimeHour());
        Assert.assertEquals(new Integer(4), recipe.getCookTimeMinute());
        Assert.assertEquals("url", recipe.getPhotoUrl());
        Assert.assertTrue(recipe.getIngredients() != null &&
                recipe.getIngredients().isEmpty());
        Assert.assertTrue(recipe.getSteps() != null &&
                recipe.getSteps().isEmpty());
    }
}
