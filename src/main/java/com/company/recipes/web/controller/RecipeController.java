package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.IngredientService;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.StepService;
import com.company.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
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
        List<Recipe> recipes;
        if(!model.containsAttribute("recipes")){
            recipes = recipeService.findAll();
            model.addAttribute("recipes", recipes);
        }

        if(!model.containsAttribute("recipe")){
            model.addAttribute("recipe", new Recipe());
        }

        model.addAttribute("categories", Recipe.Category.values());
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
    public String editRecipe(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("One or more fields have invalid input. Please try again!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/recipes/%s/edit", recipe.getId());
        }

        if(recipe.getIngredients() != null){
            recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        }

        if(recipe.getSteps() != null){
            recipe.getSteps().forEach(step -> step.setRecipe(recipe));
        }

        recipeService.save(recipe);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The recipe has been modified successfully!", FlashMessage.Status.SUCCESS));
        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }

    @RequestMapping(value = "/recipes/{recipeId}/detail")
    public String recipeDetails(@PathVariable Long recipeId, Model model){
        Recipe recipe = recipeService.findOne(recipeId);
        if(!recipe.getFavoriteUsers().contains(recipe.getUser())){
            model.addAttribute("favoredByUser", false);
        }else{
            model.addAttribute("favoredByUser", true);
        }
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", recipe.getIngredients());
        model.addAttribute("steps", recipe.getSteps());
        return "recipe/detail";
    }

    @RequestMapping(value = "/recipes/add")
    public String addRecipeForm(Model model){
        if(!model.containsAttribute("recipe")) {
            model.addAttribute("recipe", new Recipe());
        }
        model.addAttribute("categories", Recipe.Category.values());
        model.addAttribute("action", "recipes/add-recipe");
        return "recipe/edit";
    }

    @RequestMapping(value = "/recipes/add-recipe", method = RequestMethod.POST)
    public String addRecipe(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes,
                            Principal principal){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("One or more fields have invalid input. Please try again!", FlashMessage.Status.FAILURE));
            redirectAttributes.addFlashAttribute("recipe", recipe);
            return "redirect:/recipes/add";
        }

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        recipe.setUser(user);

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

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The recipe has been added successfully!", FlashMessage.Status.SUCCESS));
        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }

    @RequestMapping(value = "/recipes/{recipeId}/delete", method = RequestMethod.POST)
    public String deleteRecipe(@PathVariable Long recipeId, RedirectAttributes redirectAttributes){
        Recipe recipe = recipeService.findOne(recipeId);
        recipeService.delete(recipe);
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The recipe has been deleted successfully!", FlashMessage.Status.SUCCESS));
        return "redirect:/";
    }

    @RequestMapping(value = "/search-by-description-containing", method = RequestMethod.GET)
    public String searchByDescriptionContaining(Recipe recipe, Model model, RedirectAttributes redirectAttributes){
        List<Recipe> recipes = recipeService.findByDescriptionContaining(recipe.getDescription());
        redirectAttributes.addFlashAttribute("recipes", recipes);
        redirectAttributes.addFlashAttribute("description", recipe.getDescription());
        return "redirect:/";
    }

    @RequestMapping(value = "/search-by-category", method = RequestMethod.GET)
    public String searchByCategory(Recipe recipe, Model model, RedirectAttributes redirectAttributes){
        List<Recipe> recipes = new ArrayList<>();
        if(recipe.getCategory() != null){
            recipes = recipeService.findByCategory(recipe.getCategory().getName());
        }
        redirectAttributes.addFlashAttribute("recipes", recipes);
        redirectAttributes.addFlashAttribute("recipe", recipe);
        return "redirect:/";
    }

    @RequestMapping(value = "/recipes/{recipeId}/detail/add-to-favorites", method = RequestMethod.POST)
    public String addToFavories(Recipe recipe, RedirectAttributes redirectAttributes, Principal principal){
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
//        List<Recipe> recipes = user.getFavoritedRecipes();
//        List<User> users = recipe.getFavoriteUsers();
        if(!user.getFavoritedRecipes().contains(recipe)){
            user.addFavoritedRecipe(recipe);
            recipe.addFavoriteUser(user);
        }else{
            user.removeFavoritedRecipe(recipe);
            recipe.removeFavoriteUser(user);
        }

        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }
}
