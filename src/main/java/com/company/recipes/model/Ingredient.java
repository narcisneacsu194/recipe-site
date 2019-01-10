package com.company.recipes.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ingredient extends BaseEntity{

    @NotNull
    @Size(min = 2)
    private String description;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(){
        super();
    }

    public Ingredient(String description){
        this();
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
