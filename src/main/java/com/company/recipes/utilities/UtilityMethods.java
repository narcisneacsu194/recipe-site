package com.company.recipes.utilities;

import com.company.recipes.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class UtilityMethods {
    public static  List<Recipe> nullAndNonNullUserFavoriteRecipeList(List<Recipe> recipes, List<Recipe> favorites){
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
