package com.company.recipes.web.controller;

import com.company.recipes.model.Ingredient;
import com.company.recipes.model.Recipe;
import com.company.recipes.model.Step;
import com.company.recipes.services.IngredientService;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private StepService stepService;

    @RequestMapping(value = {"/", "/recipes"})
    public String listRecipes(Model model){
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/index";
    }

    @RequestMapping(value = "/recipes/{recipeId}/edit")
    public String editRecipe(@PathVariable Long recipeId, Model model){
        Recipe recipe = recipeService.findOne(recipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", recipe.getIngredients());
        model.addAttribute("steps", recipe.getSteps());
        model.addAttribute("categories", Recipe.Category.values());
        model.addAttribute("action", "recipes/edit-recipe");
        return "recipe/edit";
    }

    @RequestMapping(value = "/recipes/edit-recipe", method = RequestMethod.POST)
    public String editRecipe(Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes){

        if(recipe.getIngredients() != null){
            recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        }

        if(recipe.getSteps() != null){
            recipe.getSteps().forEach(step -> step.setRecipe(recipe));
        }

        recipeService.save(recipe);
        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }

    @RequestMapping(value = "/recipes/{recipeId}/detail")
    public String recipeDetails(@PathVariable Long recipeId, Model model){
        Recipe recipe = recipeService.findOne(recipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", recipe.getIngredients());
        model.addAttribute("steps", recipe.getSteps());
        return "recipe/detail";
    }

    @RequestMapping(value = "/recipes/add")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", Recipe.Category.values());
        model.addAttribute("action", "recipes/add-recipe");
        return "recipe/edit";
    }

    @RequestMapping(value = "/recipes/add-recipe", method = RequestMethod.POST)
    public String addRecipe(Recipe recipe, Model model, BindingResult result, RedirectAttributes redirectAttributes){
        recipeService.save(recipe);

        if(recipe.getIngredients() != null){
            recipe.getIngredients().forEach(ingredient -> {
                ingredient.setRecipe(recipe);
                ingredientService.save(ingredient);
            });
        }

        if(recipe.getSteps() != null){
            recipe.getSteps().forEach(step -> {
               step.setRecipe(recipe);
               stepService.save(step);
            });
        }

        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }

    @RequestMapping(value = "/recipes/{recipeId}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable Long recipeId, RedirectAttributes redirectAttributes){
        Recipe recipe = recipeService.findOne(recipeId);
        recipeService.delete(recipe);
        return "redirect:/";
    }
}
