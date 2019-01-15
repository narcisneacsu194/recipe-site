package com.company.recipes.rest.controller;

import com.company.recipes.model.Recipe;
import com.company.recipes.pojo.RecipePojo;
import com.company.recipes.services.RecipeService;
import com.company.recipes.utilities.UtilityMethods;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestRecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getRecipes(){
        List<Recipe> recipes = recipeService.findAll();
        List<RecipePojo> recipePojoList = UtilityMethods.recipeModelListToPojoList(recipes);
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(recipePojoList), HttpStatus.OK);
    }
}
