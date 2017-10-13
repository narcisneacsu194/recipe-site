package com.company.recipes.config;

import com.company.recipes.dao.*;
import com.company.recipes.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{

    private RecipeDao recipeDao;
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public DatabaseLoader(RecipeDao recipeDao, UserDao userDao, RoleDao roleDao){
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role role = new Role("ROLE_USER");
        roleDao.save(role);
        User user = new User("user", true, "password", "password");
        user.setRole(role);
        User user2 = new User("user2", true, "password", "password");
        user2.setRole(role);
        user.encryptPasswords();
        user2.encryptPasswords();
        userDao.save(user);
        userDao.save(user2);

        IntStream.range(1, 100).forEach(value -> {
            Recipe.Category recipeCategory = Recipe.Category.BREAKFAST;
            Random random = new Random();
            int randomNum = random.nextInt(4);
            switch(randomNum){
                case 0:
                    recipeCategory = Recipe.Category.BREAKFAST;
                    break;
                case 1:
                    recipeCategory = Recipe.Category.LUNCH;
                    break;
                case 2:
                    recipeCategory = Recipe.Category.DINNER;
                    break;
                case 3:
                    recipeCategory = Recipe.Category.DESSERT;
                    break;
            }
            Recipe recipe = new Recipe("Name " + value, "Description " + value, recipeCategory,
                    value, value, "http://placehold.it/" + value + "/350x150");

            if(value < 50){
                user.addOwnedRecipe(recipe);
                recipe.setUser(user);
            }else{
                user2.addOwnedRecipe(recipe);
                recipe.setUser(user2);
            }

            IntStream.range(1, 6).forEach(value2 -> {
                Ingredient ingredient = new Ingredient("Item " + value + "-" + value2, "State " + value + "-" + value2, value2);
                Step step = new Step("Step " + value + "-" + value2);

                recipe.addIngredient(ingredient);
                recipe.addStep(step);
                ingredient.setRecipe(recipe);
                step.setRecipe(recipe);

            });


            recipeDao.save(recipe);


        });

    }
}
