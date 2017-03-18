package com.company.recipes.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StepModelTest {
    private Step step;

    @Before
    public void setUp(){
        step = new Step();
    }

    @Test
    public void setDescription_ShouldSetDescriptionForStep() throws Exception{
        step.setDescription("Description 1");
        Assert.assertEquals("Description 1", step.getDescription());
    }
}
