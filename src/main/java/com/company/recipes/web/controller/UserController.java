package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.model.User;
import com.company.recipes.services.RecipeService;
import com.company.recipes.services.UserService;
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

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String userProfile(Model model, Principal principal){
        List<Recipe> recipes = recipeService.findAllFromSpecificUser();
        User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        User actualUser = userService.findByUsername(user.getUsername());
        model.addAttribute("recipes", recipes);
        model.addAttribute("nullAndNonNullUserFavoriteRecipeList",
                nullAndNonNullUserFavoriteRecipeList(recipes, actualUser.getFavoritedRecipes()));
        return "user/profile";
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
