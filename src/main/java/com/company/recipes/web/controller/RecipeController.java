package com.company.recipes.web.controller;

import com.company.recipes.model.Ingredient;
import com.company.recipes.model.Recipe;
import com.company.recipes.model.Step;
import com.company.recipes.model.User;
import com.company.recipes.services.IngredientService;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.StepService;
import com.company.recipes.services.UserService;
import com.company.recipes.utilities.UtilityMethods;
import com.company.recipes.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URL;
import java.security.Principal;
import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final StepService stepService;

    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService, StepService stepService, UserService userService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.stepService = stepService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/recipes"})
    public String listRecipes(Model model, Principal principal){
        List<Recipe> recipes;
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        if(!model.containsAttribute("recipes")){
            recipes = recipeService.findAll();
            model.addAttribute("nullAndNonNullUserFavoriteRecipeList",
                    UtilityMethods.nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));

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
    public String editRecipe(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirectAttributes,
                             Principal principal){
        String response = validateFormValues(recipe, redirectAttributes, result,
                String.format("redirect:/recipes/%s/edit", recipe.getId()));

        if(!response.isEmpty())return response;

        setUserForIngredientsAndSteps(recipe);

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User userFromDatabase = userService.findByUsername(user.getUsername());
        Recipe recipeFromDatabase = recipeService.findOne(recipe.getId());

        recipe.setUser(userFromDatabase);
        recipe.setFavoriteUsers(recipeFromDatabase.getFavoriteUsers());

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

       String response = validateFormValues(recipe, redirectAttributes, result,
               "redirect:/recipes/add");
       if(!response.isEmpty())return response;

        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        recipe.setUser(user);

        try {
            recipeService.save(recipe);
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("uniqueConstraintError",
                    String.format("The name \"%s\" is already taken by another recipe. " +
                                    "Please try again!",
                            recipe.getName()));
            return "redirect:/recipes/add";
        }

        setUserForIngredientsAndSteps(recipe);

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
    public String searchByDescriptionContaining(Recipe recipe, RedirectAttributes redirectAttributes, Principal principal){
        List<Recipe> recipes = recipeService.findByDescriptionContaining(recipe.getDescription().trim());
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        redirectAttributes.addFlashAttribute("recipes", recipes);
        redirectAttributes.addFlashAttribute("nullAndNonNullUserFavoriteRecipeList",
                UtilityMethods.nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        redirectAttributes.addFlashAttribute("description", recipe.getDescription());
        return "redirect:/";
    }

    @RequestMapping(value = "/search-by-category", method = RequestMethod.GET)
    public String searchByCategory(Recipe recipe, RedirectAttributes redirectAttributes, Principal principal){
        List<Recipe> recipes;
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        if(recipe.getCategory() != null){
            recipes = recipeService.findByCategory(recipe.getCategory().getName());
            redirectAttributes.addFlashAttribute("nullAndNonNullUserFavoriteRecipeList",
                    UtilityMethods.nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        }else{
            recipes = recipeService.findAll();
            redirectAttributes.addFlashAttribute("nullAndNonNullUserFavoriteRecipeList",
                    UtilityMethods.nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
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

    private Boolean isValidUrl(String url){
        try {
            new URL(url).toURI();
        }catch(Exception e){
            return false;
        }

        return true;
    }

    private void setUserForIngredientsAndSteps(Recipe recipe){
        Recipe recipeFromDatabase = recipeService.findOne(recipe.getId());
        List<Ingredient> ingredients = recipeFromDatabase.getIngredients();
        List<Step> steps = recipeFromDatabase.getSteps();

        ingredients.forEach(ingredient -> {
           ingredient.setRecipe(null);
        });

        steps.forEach(step -> {
           step.setRecipe(null);
        });

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
    }

    private String validateFormValues(Recipe recipe, RedirectAttributes redirectAttributes,
                                      BindingResult result, String redirectUri){
        int errorCount = 0;
        String photoUrl = recipe.getPhotoUrl();
        if(photoUrl.isEmpty() || !isValidUrl(photoUrl)){
            redirectAttributes.addFlashAttribute("urlError", "The provided photo URL is invalid");
            errorCount++;
        }

        String timeError = "You need to specify at least an hour or a minute!";

        if(recipe.getPrepTimeHour() == null && recipe.getPrepTimeMinute() == null){
            redirectAttributes.addFlashAttribute("prepTimeError",
                    timeError);
            errorCount++;
        }

        if(recipe.getCookTimeHour() == null && recipe.getCookTimeMinute() == null){
            redirectAttributes.addFlashAttribute("cookTimeError",
                    timeError);
            errorCount++;
        }

        if(result.hasErrors()){
            errorCount++;
            List<FieldError> fieldErrorList = result.getFieldErrors();

            for (FieldError err : fieldErrorList) {
                String fieldName = err.getField();

                if ((fieldName.contains("Hour") || fieldName.contains("Minute")) &&
                        err.getDefaultMessage().contains("NumberFormatException")) {
                    redirectAttributes.addFlashAttribute(fieldName + "Error",
                            "The value you provided is not a number");
                } else if (fieldName.contains("ingredients[")) {
                    int idx = Integer.parseInt(fieldName.replaceAll("[^0-9]", ""));
                    Ingredient ingredient = recipe.getIngredients().get(idx);
                    ingredient.setError(err.getDefaultMessage());
                } else if (fieldName.contains("steps[")) {
                    int idx = Integer.parseInt(fieldName.replaceAll("[^0-9]", ""));
                    Step step = recipe.getSteps().get(idx);
                    step.setError(err.getDefaultMessage());
                } else {
                    redirectAttributes.addFlashAttribute(err.getField() + "Error", err.getDefaultMessage());
                }
            }
        }

        redirectAttributes.addFlashAttribute("recipe", recipe);

        if(errorCount > 0) return redirectUri;

        return "";
    }
}
