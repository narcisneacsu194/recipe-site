package com.company.recipes.pojo;

import com.company.recipes.enums.Category;

import java.util.List;

public class RecipePojo {
    private String author;
    private String name;
    private String description;
    private Category category;
    private Integer prepTimeHour;
    private Integer prepTimeMinute;
    private Integer cookTimeHour;
    private Integer cookTimeMinute;
    private String photoUrl;
    private List<IngredientPojo> ingredients;
    private List<StepPojo> steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTimeHour() {
        return prepTimeHour;
    }

    public void setPrepTimeHour(Integer prepTimeHour) {
        this.prepTimeHour = prepTimeHour;
    }

    public Integer getPrepTimeMinute() {
        return prepTimeMinute;
    }

    public void setPrepTimeMinute(Integer prepTimeMinute) {
        this.prepTimeMinute = prepTimeMinute;
    }

    public Integer getCookTimeHour() {
        return cookTimeHour;
    }

    public void setCookTimeHour(Integer cookTimeHour) {
        this.cookTimeHour = cookTimeHour;
    }

    public Integer getCookTimeMinute() {
        return cookTimeMinute;
    }

    public void setCookTimeMinute(Integer cookTimeMinute) {
        this.cookTimeMinute = cookTimeMinute;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<IngredientPojo> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientPojo> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepPojo> getSteps() {
        return steps;
    }

    public void setSteps(List<StepPojo> steps) {
        this.steps = steps;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
