package com.company.recipes.dao;

import com.company.recipes.enums.Category;
import com.company.recipes.model.Recipe;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = "spring.datasource.url = jdbc:mysql://localhost:3306/test_RecipeDaoTest_recipes")
public class RecipeDaoTest {
    @Autowired
    private RecipeDao dao;

    @After
    public void deleteNewRecipes(){
        List<Recipe> recipes = dao.findAll();

        recipes.forEach(recipe -> {
           if(recipe.getName().startsWith("New")){
               dao.delete(recipe.getId());
           }
        });
    }

    @Test
    public void findAll_ShouldReturn6Recipes(){
        Assert.assertThat(dao.findAll(), hasSize(6));
    }

    @Test
    public void findOne_ShouldReturnNull(){
        Assert.assertThat(dao.findOne(7L), nullValue(Recipe.class));
    }

    @Test
    public void save_ShouldPersistEntity(){
        Recipe recipe = new Recipe("New recipe 1", "Description 1",
                Category.BREAKFAST, 1, 2, 3, 4,
                "http://placehold.it/350x150");
        dao.save(recipe);
        Assert.assertThat(dao.findOne(recipe.getId()), notNullValue(Recipe.class));

        dao.delete(recipe.getId());
    }

    @Test
    public void save_ShouldPersistMultipleEntities(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("New recipe 1", "Description 1",
                Category.BREAKFAST, 1, 2, 3, 4,
                "http://placehold.it/350x150");
        recipes.add(recipe);
        Recipe recipe2 = new Recipe("New recipe 2", "Description 2",
                Category.BREAKFAST, 1, 2, 3, 4,
                "http://placehold.it/350x150");
        recipes.add(recipe2);
        Recipe recipe3 = new Recipe("New recipe 3", "Description 3",
                Category.BREAKFAST, 1, 2, 3, 4,
                "http://placehold.it/350x150");
        recipes.add(recipe3);

        dao.save(recipes);

        Assert.assertThat(dao.findAll(), hasSize(9));
    }

    @Test
    public void delete_ShouldDeleteEntity(){
        Recipe recipe = new Recipe("New recipe 2", "Description 1",
                Category.BREAKFAST, 1, 2, 3, 4, "http://placehold.it/350x150");
        dao.save(recipe);
        Long recipeId = recipe.getId();
        dao.delete(recipe);
        Assert.assertThat(dao.findOne(recipeId), nullValue(Recipe.class));
    }

}
