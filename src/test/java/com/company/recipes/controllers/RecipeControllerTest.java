package com.company.recipes.controllers;

import com.company.recipes.enums.Category;
import com.company.recipes.model.Ingredient;
import com.company.recipes.model.Recipe;
import com.company.recipes.model.Step;
import com.company.recipes.model.User;
import com.company.recipes.services.IngredientService;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.StepService;
import com.company.recipes.services.UserService;
import com.company.recipes.web.controller.RecipeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class RecipeControllerTest{

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private StepService stepService;

    @Mock
    private UserService userService;


    private RecipeController recipeController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService,
                ingredientService, stepService, userService);

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testListRecipes() throws Exception {
        User user = new User();
        user.setUsername("user");

        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipes.add(recipe);

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findAll()).thenReturn(recipes);

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("username"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(1)).findAll();
    }

    @Test
    public void testEditRecipeGET() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList(new Ingredient("Ingredient 1"),
                new Ingredient("Ingredient 2")));
        recipe.setSteps(Arrays.asList(new Step("Step 1"),
                new Step("Step 2")));
        when(recipeService.findOne(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/edit"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("ingredients"))
                .andExpect(model().attributeExists("steps"))
                .andExpect(model().attributeExists("action"));

        verify(recipeService, times(1)).findOne(anyLong());
    }

    @Test
    public void testEditRecipePOST() throws Exception{
        User user = new User();
        user.setUsername("user");
        User user2 = new User();
        user2.setUsername("user2");
        Recipe recipe = new Recipe();
        List<Ingredient> ingredients =
                Arrays.asList(new Ingredient("Ingredient 1"));
        List<Step> steps = Arrays.asList(new Step("Step 1"));
        recipe.setFavoriteUsers(Arrays.asList(user2));
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findOne(anyLong()))
                .thenReturn(recipe);

        mockMvc.perform(post("/recipes/edit-recipe")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "1")
        .param("name", "recipe1")
        .param("category", Category.BREAKFAST.toString())
        .param("prepTimeHour", "1")
        .param("cookTimeHour", "2")
                .param("ingredients", ingredients.get(0).getDescription())
                .param("steps", steps.get(0).getDescription())
        .param("photoUrl", "http://www.google.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/1/detail"))
                .andExpect(flash().attributeExists("flash"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(2)).findOne(anyLong());
        verify(recipeService, times(1)).save(any(Recipe.class));
        verify(ingredientService, times(1)).save(any(Ingredient.class));
        verify(stepService, times(1)).save(any(Step.class));
    }

    @Test
    public void testRecipeDetailsWithoutRecipeBeingFavored() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList(new Ingredient("Ingredient 1")));
        recipe.setSteps(Arrays.asList(new Step("Step 1")));

        when(recipeService.findOne(anyLong())).thenReturn(recipe);
        when(userService.findByUsername(anyString())).thenReturn(user);

        mockMvc.perform(get("/recipes/1/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/detail"))
                .andExpect(model().attributeExists("favoredByUser"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("ingredients"))
                .andExpect(model().attributeExists("steps"));

        verify(recipeService, times(1)).findOne(anyLong());
        verify(userService, times(1)).findByUsername(anyString());
    }

    @Test
    public void testRecipeDetailsWithRecipeBeingFavored() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList(new Ingredient("Ingredient 1")));
        recipe.setSteps(Arrays.asList(new Step("Step 1")));
        recipe.setFavoriteUsers(Arrays.asList(user));

        when(recipeService.findOne(anyLong())).thenReturn(recipe);
        when(userService.findByUsername(anyString())).thenReturn(user);

        mockMvc.perform(get("/recipes/1/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/detail"))
                .andExpect(model().attributeExists("favoredByUser"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("ingredients"))
                .andExpect(model().attributeExists("steps"));

        verify(recipeService, times(1)).findOne(anyLong());
        verify(userService, times(1)).findByUsername(anyString());
    }

    @Test
    public void testAddRecipeForm() throws Exception{
        mockMvc.perform(get("/recipes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/edit"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("action"));
    }

    @Test
    public void testAddValidRecipe() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList(new Ingredient("Ingredient 1")));
        recipe.setSteps(Arrays.asList(new Step("Step 1")));

        when(recipeService.findOne(anyLong()))
                .thenReturn(recipe);

        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "recipe1")
                .param("category", Category.BREAKFAST.toString())
                .param("prepTimeHour", "1")
                .param("cookTimeHour", "2")
                .param("photoUrl", "http://www.google.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/1/detail"))
                .andExpect(flash().attributeExists("flash"));

        verify(recipeService, times(1)).save(any(Recipe.class));
        verify(recipeService, times(1)).findOne(anyLong());
    }

    @Test
    public void testAddRecipeWithNonUniqueName() throws Exception {

        doThrow(Exception.class)
                .when(recipeService)
                .save(any(Recipe.class));

        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "recipe1")
                .param("category", Category.BREAKFAST.toString())
                .param("prepTimeHour", "1")
                .param("cookTimeHour", "2")
                .param("photoUrl", "http://www.google.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/add"))
                .andExpect(flash().attributeExists("uniqueConstraintError"));
    }

    @Test
    public void testDeleteRecipe() throws Exception{
        Recipe recipe = new Recipe();

        when(recipeService.findOne(anyLong())).thenReturn(recipe);

        mockMvc.perform(post("/recipes/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(flash().attributeExists("flash"));

        verify(recipeService, times(1)).findOne(anyLong());
        verify(recipeService, times(1)).delete(any(Recipe.class));
    }

    @Test
    public void testSearchByDescriptionContainingWithNullDescription() throws Exception{
        mockMvc.perform(get("/search-by-description-containing"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testSearchByDescriptionContainingWithNonNullDescription() throws Exception{
        List<Recipe> recipes = Arrays.asList(new Recipe());
        User user = new User();

        when(recipeService.findByDescriptionContaining(anyString()))
                .thenReturn(recipes);
        when(userService.findByUsername(anyString()))
                .thenReturn(user);

        mockMvc.perform(get("/search-by-description-containing")
                .param("description", "Description 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(flash().attributeExists("recipes"))
                .andExpect(flash().attributeExists("nullAndNonNullUserFavoriteRecipeList"))
                .andExpect(flash().attributeExists("description"));
    }

    @Test
    public void testSearchByCategoryWithNullCategory() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();
        List<Recipe> recipes = Arrays.asList(recipe);

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findAll())
                .thenReturn(recipes);

        mockMvc.perform(get("/search-by-category"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(1)).findAll();
    }

    @Test
    public void testSearchByCategoryWithNonNullCategory() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();
        List<Recipe> recipes = Arrays.asList(recipe);

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findByCategory(anyString()))
                .thenReturn(recipes);

        mockMvc.perform(get("/search-by-category")
                .param("category", Category.BREAKFAST.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(flash().attributeExists("nullAndNonNullUserFavoriteRecipeList"))
                .andExpect(flash().attributeExists("recipes"))
                .andExpect(flash().attributeExists("recipe"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(1)).findByCategory(anyString());
    }

    @Test
    public void testRemoveFromFavorites() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();
        List<Recipe> recipes = Arrays.asList(recipe);
        user.setFavoritedRecipes(recipes);

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findOne(anyLong()))
                .thenReturn(recipe);

        mockMvc.perform(post("/recipes/1/detail/add-to-favorites"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/1/detail"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(1)).findOne(anyLong());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void testAddToFavorites() throws Exception{
        User user = new User();
        Recipe recipe = new Recipe();

        when(userService.findByUsername(anyString()))
                .thenReturn(user);
        when(recipeService.findOne(anyLong()))
                .thenReturn(recipe);

        mockMvc.perform(post("/recipes/1/detail/add-to-favorites"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/1/detail"));

        verify(userService, times(1)).findByUsername(anyString());
        verify(recipeService, times(1)).findOne(anyLong());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void testAddRecipeUsingNullValuesForFieldsAndInvalidURL() throws Exception{
        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("photoUrl", "Invalid URL"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/add"))
                .andExpect(flash().attributeExists("urlError"))
                .andExpect(flash().attributeExists("prepTimeError"))
                .andExpect(flash().attributeExists("cookTimeError"))
                .andExpect(flash().attributeExists("nameError"))
                .andExpect(flash().attributeExists("categoryError"))
                .andExpect(flash().attributeExists("recipe"))
                .andExpect(flash().attributeCount(6));
    }

    @Test
    public void testAddRecipeUsingInvalidRecipeFieldValues() throws Exception{
        mockMvc.perform(post("/recipes/add-recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "R")
                .param("category", Category.BREAKFAST.toString())
                .param("prepTimeHour", "0")
                .param("prepTimeMinute", "1")
                .param("cookTimeHour", "0")
                .param("cookTimeMinute", "1")
                .param("ingredients", " ")
                .param("steps", " ")
                .param("photoUrl", "http://www.google.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/add"))
                .andExpect(flash().attributeExists("prepTimeHourError"))
                .andExpect(flash().attributeExists("prepTimeMinuteError"))
                .andExpect(flash().attributeExists("cookTimeHourError"))
                .andExpect(flash().attributeExists("cookTimeMinuteError"))
                .andExpect(flash().attributeExists("nameError"))
                .andExpect(flash().attributeExists("recipe"))
                .andExpect(flash().attributeCount(6));
    }
}
