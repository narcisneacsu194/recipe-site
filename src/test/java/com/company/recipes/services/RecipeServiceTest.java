//package com.company.recipes.services;
//
//import com.company.recipes.dao.RecipeDao;
//import com.company.recipes.model.Recipe;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.hamcrest.Matchers.instanceOf;
//import static org.junit.Assert.*;
//@RunWith(MockitoJUnitRunner.class)
//public class RecipeServiceTest {
//    @Mock
//    private RecipeDao dao;
//
//    @InjectMocks
//    private RecipeService service = new RecipeServiceImpl();
//
//    @Test
//    public void findAll_ShouldReturnTwo() throws Exception{
//        List<Recipe> recipes = Arrays.asList(
//             new Recipe("Name 1", "Description 1", Recipe.Category.BREAKFAST,
//                     32, 23, "http://placehold.it/350x150"),
//                new Recipe("Name 2", "Description 2", Recipe.Category.DINNER,
//                        32, 23, "http://placehold.it/350x150")
//        );
//
//        when(dao.findAll()).thenReturn(recipes);
//
//        assertEquals("findAll should return two recipes", 2,
//                service.findAll().size());
//
//        verify(dao).findAll();
//    }
//
//    @Test
//    public void findOne_ShouldReturnOne() throws Exception{
//        when(dao.findOne(1L)).thenReturn(new Recipe("Name 1", "Description 1", Recipe.Category.BREAKFAST,
//                23, 32, "http://placehold.it/350x150"));
//        assertThat(service.findOne(1L), instanceOf(Recipe.class));
//        verify(dao).findOne(1L);
//    }
//}
