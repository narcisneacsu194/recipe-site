package com.company.recipes.model;

import com.company.recipes.enums.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        recipe.setCookTimeHour(1);
        Assert.assertEquals(new Integer(1), recipe.getCookTimeHour());
    }

    @Test
    public void setPhotoUrl_ShouldSetPhotoUrlForRecipe(){
        recipe.setPhotoUrl("url");
        Assert.assertEquals("url", recipe.getPhotoUrl());
    }
}
