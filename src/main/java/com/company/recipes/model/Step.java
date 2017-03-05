package com.company.recipes.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Step extends BaseEntity{
    private String description;

    @ManyToOne
    private Recipe recipe;

    protected Step(){
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
