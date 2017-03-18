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
    public void setItem_ShouldSetItemForIngredient() throws Exception{
        ingredient.setItem("Item 1");
        Assert.assertEquals("Item 1", ingredient.getItem());
    }

    @Test
    public void setCondition_ShouldSetConditionForIngredient() throws Exception{
        ingredient.setCondition("Condition 1");
        Assert.assertEquals("Condition 1", ingredient.getCondition());
    }

    @Test
    public void setQuantity_ShouldSetQuantityForIngredient() throws Exception{
        ingredient.setQuantity(1);
        Assert.assertEquals((Integer)1, ingredient.getQuantity());
    }

}
