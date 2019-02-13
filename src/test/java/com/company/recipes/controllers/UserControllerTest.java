package com.company.recipes.controllers;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.UserService;
import com.company.recipes.web.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        UserController userController = new UserController(recipeService, userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testUserProfile() throws Exception {
        List<Recipe> recipes = Arrays.asList(new Recipe());
        User user = new User();
        user.setFavoritedRecipes(recipes);

        when(recipeService.findAllFromSpecificUser())
                .thenReturn(recipes);
        when(userService.findByUsername(anyString()))
                .thenReturn(user);

        mockMvc.perform(get("/user/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attributeExists("favoredRecipes"))
                .andExpect(model().attributeExists("nullAndNonNullUserFavoriteRecipeList"));

        verify(recipeService, times(1)).findAllFromSpecificUser();
        verify(userService, times(1)).findByUsername(anyString());
    }
}
