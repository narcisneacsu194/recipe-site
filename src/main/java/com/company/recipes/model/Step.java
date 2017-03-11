package com.company.recipes.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Step extends BaseEntity{

    @NotNull
    @Size(min = 2, max = 1000)
    private String description;

    @ManyToOne
    private Recipe recipe;

    public Step(){
        super();
    }

    public Step(String description){
        this();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
