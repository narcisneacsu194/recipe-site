package com.company.recipes.services;

import com.company.recipes.dao.RecipeDao;
import com.company.recipes.enums.Category;
import com.company.recipes.model.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
}
