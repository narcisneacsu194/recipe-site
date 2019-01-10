//package com.company.recipes.dao;
//
//import com.company.recipes.model.Ingredient;
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
//
//@RunWith(value = SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//                properties = "spring.datasource.url = jdbc:h2:./database/test-IngredientDaoTest-ingredients;DB_CLOSE_ON_EXIT=FALSE")
//public class IngredientDaoTest {
//    @Autowired
//    private IngredientDao ingredientDao;
//
//    @Test
//    public void findAll_ShouldReturn694Ingredients() throws Exception{
//        Assert.assertThat(ingredientDao.findAll(), hasSize(694));
//    }
//
//    @Test
//    public void findOne_ShouldReturnNull() throws Exception{
//        Assert.assertThat(ingredientDao.findOne(695L), nullValue(Ingredient.class));
//    }
//
//    @Test
//    public void save_ShouldPersistEntity() throws Exception{
//        Ingredient ingredient = new Ingredient("Item 1", "Condition 1",
//                33);
//        ingredientDao.save(ingredient);
//        Assert.assertThat(ingredientDao.findOne(ingredient.getId()), notNullValue(Ingredient.class));
//    }
//
//    @Test
//    public void save_ShouldPersistMultipleEntities() throws Exception{
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient = new Ingredient("Item 1", "Condition 1", 33);
//        ingredients.add(ingredient);
//        Ingredient ingredient2 = new Ingredient("Item 2", "Condition 2", 33);
//        ingredients.add(ingredient2);
//        Ingredient ingredient3 = new Ingredient("Item 3", "Condition 3", 33);
//        ingredients.add(ingredient3);
//
//        ingredientDao.save(ingredients);
//
//        Assert.assertThat(ingredientDao.findAll(), hasSize(697));
//    }
//
//    @Test
//    public void delete_ShouldDeleteEntity() throws Exception{
//        Ingredient ingredient = new Ingredient("Item 1", "Condition 1", 33);
//        ingredientDao.save(ingredient);
//        Long ingredientId = ingredient.getId();
//        ingredientDao.delete(ingredient);
//        Assert.assertThat(ingredientDao.findOne(ingredientId), nullValue(Ingredient.class));
//    }
//}
