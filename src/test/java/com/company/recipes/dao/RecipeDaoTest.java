package com.company.recipes.dao;

import com.company.recipes.Application;
import com.company.recipes.model.Recipe;
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
@DatabaseSetup("classpath:recipes.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class RecipeDaoTest {
    @Autowired
    private RecipeDao dao;

    @Test
    public void findAll_ShouldReturnThree() throws Exception{
        Assert.assertThat(dao.findAll(), hasSize(3));
    }

    @Test
    public void findOne_ShouldReturnNull() throws Exception{
        Assert.assertThat(dao.findOne(5L), nullValue(Recipe.class));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception{
        Recipe recipe = new Recipe("Recipe 1", "Description 1",
                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
        dao.save(recipe);
        Assert.assertThat(dao.findOne(recipe.getId()), notNullValue(Recipe.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities() throws Exception{
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("Recipe 1", "Description 1",
                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
        recipes.add(recipe);
        Recipe recipe2 = new Recipe("Recipe 2", "Description 2",
                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
        recipes.add(recipe2);
        Recipe recipe3 = new Recipe("Recipe 3", "Description 3",
                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
        recipes.add(recipe3);

        dao.save(recipes);

        Assert.assertThat(dao.findAll(), hasSize(6));
    }

    @Test
    public void delete_ShouldDeleteEntity() throws Exception{
        Recipe recipe = new Recipe("Recipe 1", "Description 1",
                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
        dao.save(recipe);
        Long recipeId = recipe.getId();
        dao.delete(recipe);
        Assert.assertThat(dao.findOne(recipeId), nullValue(Recipe.class));
    }

}
