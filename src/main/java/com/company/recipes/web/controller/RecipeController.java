package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = {"/", "/recipes"})
    public String listRecipes(Model model){
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe/index";
    }

    @RequestMapping(value = "/recipes/{recipeId}/edit")
    public String editRecipe(Model model){
        return "recipe/edit";
    }

    @RequestMapping(value = "/recipes/{recipeId}/detail")
    public String recipeDetails(Model model){
        return "recipe/detail";
    }

    @RequestMapping(value = "/recipes/add-new-recipe")
    public String addRecipe(){
        return "recipe/edit";
    }

    @RequestMapping(value = "/recipes/{recipeId}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable Long recipeId, RedirectAttributes redirectAttributes){
        Recipe recipe = recipeService.findOne(recipeId);
        recipeService.delete(recipe);
        return "redirect:/";
    }
}
