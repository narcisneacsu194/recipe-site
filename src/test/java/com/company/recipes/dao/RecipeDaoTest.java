//package com.company.recipes.dao;
//
//import com.company.recipes.model.RecipePojo;
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
//        Assert.assertThat(dao.findOne(101L), nullValue(RecipePojo.class));
//    }
//
//    @Test
//    public void save_ShouldPersistEntity() throws Exception{
//        RecipePojo recipe = new RecipePojo("RecipePojo 1", "Description 1",
//                RecipePojo.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        dao.save(recipe);
//        Assert.assertThat(dao.findOne(recipe.getId()), notNullValue(RecipePojo.class));
//    }
//
//    @Test
//    public void save_ShouldPersistMultipleEntities() throws Exception{
//        List<RecipePojo> recipes = new ArrayList<>();
//        RecipePojo recipe = new RecipePojo("RecipePojo 1", "Description 1",
//                RecipePojo.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe);
//        RecipePojo recipe2 = new RecipePojo("RecipePojo 2", "Description 2",
//                RecipePojo.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe2);
//        RecipePojo recipe3 = new RecipePojo("RecipePojo 3", "Description 3",
//                RecipePojo.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        recipes.add(recipe3);
//
//        dao.save(recipes);
//
//        Assert.assertThat(dao.findAll(), hasSize(103));
//    }
//
//    @Test
//    public void delete_ShouldDeleteEntity() throws Exception{
//        RecipePojo recipe = new RecipePojo("RecipePojo 1", "Description 1",
//                RecipePojo.Category.BREAKFAST, 23, 32, "http://placehold.it/350x150");
//        dao.save(recipe);
//        Long recipeId = recipe.getId();
//        dao.delete(recipe);
//        Assert.assertThat(dao.findOne(recipeId), nullValue(RecipePojo.class));
//    }
//
//}
