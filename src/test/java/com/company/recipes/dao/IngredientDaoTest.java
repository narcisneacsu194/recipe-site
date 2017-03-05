package com.company.recipes.dao;

import com.company.recipes.Application;
import com.company.recipes.model.Ingredient;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:ingredients.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class IngredientDaoTest {
    @Autowired
    private IngredientDao dao;

    @Test
    public void findAll_ShouldReturnThree() throws Exception{
        Assert.assertThat(dao.findAll(), hasSize(3));
    }

    @Test
    public void findOne_ShouldReturnNull() throws Exception{
        Assert.assertThat(dao.findOne(5L), nullValue(Ingredient.class));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception{
        Ingredient ingredient = new Ingredient("Item 1", "Condition 1",
                33);
        dao.save(ingredient);
        Assert.assertThat(dao.findOne(ingredient.getId()), notNullValue(Ingredient.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities() throws Exception{
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("Item 1", "Condition 1", 33);
        ingredients.add(ingredient);
        Ingredient ingredient2 = new Ingredient("Item 2", "Condition 2", 33);
        ingredients.add(ingredient2);
        Ingredient ingredient3 = new Ingredient("Item 3", "Condition 3", 33);
        ingredients.add(ingredient3);

        dao.save(ingredients);

        Assert.assertThat(dao.findAll(), hasSize(6));
    }

    @Test
    public void delete_ShouldDeleteEntity() throws Exception{
        Ingredient ingredient = new Ingredient("Item 1", "Condition 1", 33);
        dao.save(ingredient);
        Long ingredientId = ingredient.getId();
        dao.delete(ingredient);
        Assert.assertThat(dao.findOne(ingredientId), nullValue(Ingredient.class));
    }
}
