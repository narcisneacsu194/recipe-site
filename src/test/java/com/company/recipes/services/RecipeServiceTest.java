package com.company.recipes.services;

import com.company.recipes.dao.RecipeDao;
import com.company.recipes.enums.Category;
import com.company.recipes.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {
    @Mock
    private RecipeDao dao;

    @InjectMocks
    private RecipeService service = new RecipeServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo(){
        List<Recipe> recipes = Arrays.asList(
             new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                     1, 2, 3, 4, "http://placehold.it/350x150"),
                new Recipe("Name 2", "Description 2", Category.DINNER,
                        1, 2, 3, 4, "http://placehold.it/350x150")
        );

        when(dao.findAll()).thenReturn(recipes);

        assertEquals("findAll should return two recipes", 2,
                service.findAll().size());

        verify(dao, times(1)).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne(){
        when(dao.findOne(1L)).thenReturn(new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150"));
        assertThat(service.findOne(1L), instanceOf(Recipe.class));
        verify(dao, times(1)).findOne(anyLong());
    }

    @Test
    public void saveOneRecipe_ShouldCallDaoSaveMethodOnce(){
        Recipe recipe = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        service.save(recipe);
        verify(dao, times(1)).save(recipe);
    }

    @Test
    public void saveRecipeList_ShouldCallDaoSaveMethodOnce(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        Recipe recipe2 = new Recipe("Name 2", "Description 2", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        recipes.add(recipe1);recipes.add(recipe2);
        service.save(recipes);

        verify(dao, times(1)).save(recipes);
    }

    @Test
    public void delete_ShouldCallDaoDeleteMethodOnce(){
        Recipe recipe = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        service.delete(recipe);
        verify(dao, times(1)).delete(recipe);
    }

    @Test
    public void findByDescriptionContaining_ShouldReturnAListOfRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        recipes.add(recipe);
        when(dao.findByDescriptionContaining(anyString())).thenReturn(recipes);
        List<Recipe> returnedRecipes =
                service.findByDescriptionContaining("Description 1");
        assertEquals("findByDescriptionContaining should return a list containing one recipe.",
                1, returnedRecipes.size());
        assertEquals("findByDescriptionContaining doesn't return the right recipe.",
                recipe, returnedRecipes.get(0));
        verify(dao, times(1)).findByDescriptionContaining(anyString());
    }

    @Test
    public void findByCategory_ShouldReturnAListOfRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        recipes.add(recipe);
        when(dao.findByCategory(Category.BREAKFAST)).thenReturn(recipes);
        List<Recipe> returnedRecipes =
                service.findByCategory("Breakfast");
        assertEquals("findByCategory should return a list containing one recipe.",
                1, returnedRecipes.size());
        assertEquals("findByCategory doesn't return the right recipes.", recipe,
                returnedRecipes.get(0));
        verify(dao, times(1)).findByCategory(Category.BREAKFAST);
    }

    @Test
    public void findAllFromSpecificUser_ShouldReturnAListOfRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe("Name 1", "Description 1", Category.BREAKFAST,
                1, 2, 3, 4, "http://placehold.it/350x150");
        recipes.add(recipe);
        when(dao.findAllFromSpecificUser()).thenReturn(recipes);
        List<Recipe> returnedRecipes =
                service.findAllFromSpecificUser();
        assertEquals("findAllFromSpecificUser should return a list containing one recipe.", 1,
                returnedRecipes.size());
        assertEquals("findAllFromSpecificUser doesn't return the right recipes.",
                recipe, returnedRecipes.get(0));
        verify(dao, times(1)).findAllFromSpecificUser();
    }
}
