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
    public void setName_ShouldSetNameForRecipe() throws Exception{
        recipe.setName("Name 1");
        Assert.assertEquals("Name 1", recipe.getName());
    }

    @Test
    public void setDescription_ShouldSetDescriptionForRecipe() throws Exception{
        recipe.setDescription("Description 1");
        Assert.assertEquals("Description 1", recipe.getDescription());
    }

    @Test
    public void setCategory_ShouldSetCategoryForRecipe() throws Exception{
        recipe.setCategory(Category.BREAKFAST);
        Assert.assertEquals(Category.BREAKFAST, recipe.getCategory());
    }

//    @Test
//    public void setPrepTime_ShouldSetPrepTimeForRecipe() throws Exception{
//        recipe.setPrepTime(1);
//        Assert.assertEquals((Integer)1, recipe.getPrepTime());
//    }
//
//    @Test
//    public void setCookTime_ShouldSetCookTimeForRecipe() throws Exception{
//        recipe.setCookTime(1);
//        Assert.assertEquals((Integer)1, recipe.getCookTime());
//    }

    @Test
    public void setPhotoUrl_ShouldSetPhotoUrlForRecipe() throws Exception{
        recipe.setPhotoUrl("url");
        Assert.assertEquals("url", recipe.getPhotoUrl());
    }
}
