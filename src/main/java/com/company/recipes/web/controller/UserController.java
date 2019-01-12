package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.UserService;
import com.company.recipes.utilities.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final RecipeService recipeService;

    private final UserService userService;

    @Autowired
    public UserController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String userProfile(Model model, Principal principal){
        List<Recipe> recipes = recipeService.findAllFromSpecificUser();
        List<Recipe> ownedRecipes = recipeService.findAllFromSpecificUser();
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        model.addAttribute("recipes", recipes);
        List<Recipe> favoredRecipes = actualUser.getFavoritedRecipes();
        model.addAttribute("recipes", ownedRecipes);
        model.addAttribute("favoredRecipes", favoredRecipes);
        model.addAttribute("nullAndNonNullUserFavoriteRecipeList",
                UtilityMethods.nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        UtilityMethods.nullAndNonNullUserFavoriteRecipeList(ownedRecipes, actualUser.getFavoritedRecipes());
        return "user/profile";
    }
}
