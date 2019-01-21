package com.company.recipes.dao;

import com.company.recipes.model.Ingredient;
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

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = "spring.datasource.url = jdbc:mysql://localhost:3306/test_IngredientDaoTest_ingredients")
public class IngredientDaoTest {
    @Autowired
    private IngredientDao ingredientDao;

    @After
    public void deleteNewIngredients(){
        List<Ingredient> ingredients = ingredientDao.findAll();

        ingredients.forEach(ingredient -> {
            if(ingredient.getDescription().startsWith("New")){
                ingredientDao.delete(ingredient.getId());
            }
        });
    }

    @Test
    public void findAll_ShouldReturn30Ingredients(){
        Assert.assertThat(ingredientDao.findAll(), hasSize(30));
    }

    @Test
    public void findOne_ShouldReturnNull(){
        Assert.assertThat(ingredientDao.findOne(31L), nullValue(Ingredient.class));
    }

    @Test
    public void save_ShouldPersistEntity(){
        Ingredient ingredient = new Ingredient("New Ingredient 1");
        ingredientDao.save(ingredient);
        Assert.assertThat(ingredientDao.findOne(ingredient.getId()), notNullValue(Ingredient.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities(){
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("New Ingredient 1");
        ingredients.add(ingredient);
        Ingredient ingredient2 = new Ingredient("New Ingredient 2");
        ingredients.add(ingredient2);
        Ingredient ingredient3 = new Ingredient("New Ingredient 3");
        ingredients.add(ingredient3);

        ingredientDao.save(ingredients);

        Assert.assertThat(ingredientDao.findAll(), hasSize(33));
    }

    @Test
    public void delete_ShouldDeleteEntity(){
        Ingredient ingredient = new Ingredient("New Ingredient 1");
        ingredientDao.save(ingredient);
        Long ingredientId = ingredient.getId();
        ingredientDao.delete(ingredient);
        Assert.assertThat(ingredientDao.findOne(ingredientId), nullValue(Ingredient.class));
    }
}
