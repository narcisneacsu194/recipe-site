package com.company.recipes.web.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String userProfile(Model model){
        List<Recipe> recipes = recipeService.findAllFromSpecificUser();
        model.addAttribute("recipes", recipes);
        return "user/profile";
    }
}
