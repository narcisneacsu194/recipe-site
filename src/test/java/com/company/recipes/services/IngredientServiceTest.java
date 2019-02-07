package com.company.recipes.services;

import com.company.recipes.dao.IngredientDao;
import com.company.recipes.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @Mock
    private IngredientDao dao;

    @InjectMocks
    private IngredientService service = new IngredientServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo(){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("New Ingredient 1"),
                new Ingredient("New Ingredient 2")
        );

        when(dao.findAll()).thenReturn(ingredients);

        assertEquals("findAll should return two ingredients", 2,
                service.findAll().size());

        verify(dao, times(1)).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne(){
        when(dao.findOne(1L)).thenReturn(new Ingredient("New Ingredient 1"));
        assertThat(service.findOne(1L), instanceOf(Ingredient.class));
        verify(dao, times(1)).findOne(anyLong());
    }

    @Test
    public void saveOneIngredient_ShouldCallDaoSaveMethodOnce(){
        Ingredient ingredient = new Ingredient("Ingredient 1");
        service.save(ingredient);
        verify(dao, times(1)).save(ingredient);
    }

    @Test
    public void saveIngredientList_ShouldCallDaoSaveMethodOnce(){
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient("ingredient1");
        Ingredient ingredient2 = new Ingredient("ingredient2");
        ingredients.add(ingredient1);ingredients.add(ingredient2);
        service.save(ingredients);
        verify(dao, times(1)).save(ingredients);
    }

    @Test
    public void delete_ShouldCallDaoDeleteMethodOnce(){
        Ingredient ingredient = new Ingredient("Ingredient 1");
        service.delete(ingredient);
        verify(dao, times(1)).delete(ingredient);
    }
}
