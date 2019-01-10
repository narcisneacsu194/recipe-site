//package com.company.recipes.dao;
//
//import com.company.recipes.model.Recipe;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//                properties = "spring.datasource.url = jdbc:h2:./database/test-RecipeDaoTest-recipes;DB_CLOSE_ON_EXIT=FALSE")
//public class RecipeDaoTest {
//    @Autowired
//    private RecipeDao dao;
//
//    @Test
//    public void findAll_ShouldReturn100Recipes() throws Exception{
//        Assert.assertThat(dao.findAll(), hasSize(100));
//    }
//
//    @Test
//    public void findOne_ShouldReturnNull() throws Exception{
//        Assert.assertThat(dao.findOne(101L), nullValue(Recipe.class));
//    }
//
//    @Test
//    public void save_ShouldPersistEntity() throws Exception{
//        Recipe recipe = new Recipe("Recipe 1", "Description 1",
//                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        dao.save(recipe);
//        Assert.assertThat(dao.findOne(recipe.getId()), notNullValue(Recipe.class));
//    }
//
//    @Test
//    public void save_ShouldPersistMultipleEntities() throws Exception{
//        List<Recipe> recipes = new ArrayList<>();
//        Recipe recipe = new Recipe("Recipe 1", "Description 1",
//                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe);
//        Recipe recipe2 = new Recipe("Recipe 2", "Description 2",
//                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe2);
//        Recipe recipe3 = new Recipe("Recipe 3", "Description 3",
//                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe3);
//
//        dao.save(recipes);
//
//        Assert.assertThat(dao.findAll(), hasSize(103));
//    }
//
//    @Test
//    public void delete_ShouldDeleteEntity() throws Exception{
//        Recipe recipe = new Recipe("Recipe 1", "Description 1",
//                Recipe.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        dao.save(recipe);
//        Long recipeId = recipe.getId();
//        dao.delete(recipe);
//        Assert.assertThat(dao.findOne(recipeId), nullValue(Recipe.class));
//    }
//
//}
