package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.UserService;
import com.company.recipes.utilities.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String userProfile(Model model, @AuthenticationPrincipal User user){
        List<Recipe> ownedRecipes = recipeService.findAllFromSpecificUser();
        User actualUser = userService.findByUsername(user.getUsername());
        List<Recipe> favoredRecipes = actualUser.getFavoritedRecipes();
        model.addAttribute("recipes", ownedRecipes);
        model.addAttribute("favoredRecipes", favoredRecipes);
        model.addAttribute("nullAndNonNullUserFavoriteRecipeList",
                UtilityMethods.nullAndNonNullUserFavoriteRecipeList(ownedRecipes, actualUser.getFavoritedRecipes()));
        return "user/profile";
    }
}
