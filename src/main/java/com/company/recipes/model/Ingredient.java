package com.company.recipes.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient extends BaseEntity{
    private String item;
    private String condition;
    private Integer quantity;

    @ManyToOne
    private Recipe recipe;

    protected Ingredient(){
        super();
    }

    public Ingredient(String item, String condition, Integer quantity){
        this();
        this.item = item;
        this.condition = condition;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
