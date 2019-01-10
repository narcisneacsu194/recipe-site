package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.IngredientService;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.StepService;
import com.company.recipes.services.UserService;
import com.company.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private StepService stepService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/recipes"})
    public String listRecipes(Model model, Principal principal){
        List<Recipe> recipes;
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        if(!model.containsAttribute("recipes")){
            recipes = recipeService.findAll();
            model.addAttribute("nullAndNonNullUserFavoriteRecipeList",
                    nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));

            model.addAttribute("recipes", recipes);
        }

        if(!model.containsAttribute("recipe")){
            model.addAttribute("recipe", new Recipe());
        }

        model.addAttribute("categories", Recipe.Category.values());
        model.addAttribute("username", user.getUsername());
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
    public String recipeDetails(@PathVariable Long recipeId, Model model, Principal principal){
        Recipe recipe = recipeService.findOne(recipeId);
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        if(!recipe.getFavoriteUsers().contains(actualUser)){
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

            return redirectBackToForm("One or more fields have invalid input. Please try again!",
                    redirectAttributes, recipe);
        }

        if(recipe.getPrepTimeHour() == null && recipe.getPrepTimeMinute() == null){
            return redirectBackToForm("You need to specify at least an hour or a minute for the prep time property!",
                    redirectAttributes, recipe);
        }

        if(recipe.getCookTimeHour() == null && recipe.getCookTimeMinute() == null){
            return redirectBackToForm("You need to specify at least an hour or a minute for the cook time property!",
                    redirectAttributes, recipe);
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
    public String searchByDescriptionContaining(Recipe recipe, Model model, RedirectAttributes redirectAttributes, Principal principal){
        List<Recipe> recipes = recipeService.findByDescriptionContaining(recipe.getDescription());
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        redirectAttributes.addFlashAttribute("recipes", recipes);
        redirectAttributes.addFlashAttribute("nullAndNonNullUserFavoriteRecipeList",
                nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        redirectAttributes.addFlashAttribute("description", recipe.getDescription());
        return "redirect:/";
    }

    @RequestMapping(value = "/search-by-category", method = RequestMethod.GET)
    public String searchByCategory(Recipe recipe, Model model, RedirectAttributes redirectAttributes, Principal principal){
        List<Recipe> recipes = new ArrayList<>();
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        if(recipe.getCategory() != null){
            recipes = recipeService.findByCategory(recipe.getCategory().getName());
            redirectAttributes.addFlashAttribute("nullAndNonNullUserFavoriteRecipeList",
                    nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        }
        redirectAttributes.addFlashAttribute("recipes", recipes);
        redirectAttributes.addFlashAttribute("recipe", recipe);
        return "redirect:/";
    }

    @RequestMapping(value = "/recipes/{recipeId}/detail/add-to-favorites", method = RequestMethod.POST)
    public String addToFavories(@PathVariable Long recipeId, RedirectAttributes redirectAttributes, Principal principal){
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User user2 = userService.findByUsername(user.getUsername());
        Recipe recipe = recipeService.findOne(recipeId);

        if(!user2.getFavoritedRecipes().contains(recipe)){
            user2.addFavoritedRecipe(recipe);
            userService.save(user2);
        }else{
            user2.removeFavoritedRecipe(recipe);
            recipe.removeFavoriteUser(user2);
            userService.save(user2);
        }

        return String.format("redirect:/recipes/%s/detail", recipe.getId());
    }

    private String redirectBackToForm(String message, RedirectAttributes redirectAttributes, Recipe recipe){
        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage(message, FlashMessage.Status.FAILURE));
        redirectAttributes.addFlashAttribute("recipe", recipe);
        return "redirect:/recipes/add";
    }

    private List<Recipe> nullAndNonNullUserFavoriteRecipeList(List<Recipe> recipes, List<Recipe> favorites){
        List<Recipe> nullAndNonNullUserFavoriteRecipeList = new ArrayList<>();
        recipes.forEach(recipe -> {
            if (favorites.contains(recipe)) {
                nullAndNonNullUserFavoriteRecipeList.add(recipe);
            } else {
                nullAndNonNullUserFavoriteRecipeList.add(null);
            }
        });

        return nullAndNonNullUserFavoriteRecipeList;
    }
}
