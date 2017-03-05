package com.company.recipes.services;

import com.company.recipes.dao.IngredientDao;
import com.company.recipes.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;
@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @Mock
    private IngredientDao dao;

    @InjectMocks
    private IngredientService service = new IngredientServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception{
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Item 1", "Condition 1", 123),
                new Ingredient("Item 2", "Condition 2", 321)
        );

        when(dao.findAll()).thenReturn(ingredients);

        assertEquals("findAll should return two ingredients", 2,
                service.findAll().size());

        verify(dao).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne() throws Exception{
        when(dao.findOne(1L)).thenReturn(new Ingredient("Item 1", "Condition 1", 123));
        assertThat(service.findOne(1L), instanceOf(Ingredient.class));
        verify(dao).findOne(1L);
    }
}
