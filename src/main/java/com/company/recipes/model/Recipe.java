package com.company.recipes.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends BaseEntity{

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    private String description;

    @NotNull
    private Category category;

    @NotNull
    @Min(value = 1)
    private Integer prepTime;

    @NotNull
    @Min(value = 1)
    private Integer cookTime;

    @Valid
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @Valid
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Step> steps;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "users_favorite_recipes",
        joinColumns = @JoinColumn(name="recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> favoriteUsers = new ArrayList<>();

    //owner
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String photoUrl;

    public Recipe(){
        super();
    }

    public Recipe(String name, String description, Category category,
                  Integer prepTime, Integer cookTime, String photoUrl){
        this();
        this.name = name;
        this.description = description;
        this.category = category;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.photoUrl = photoUrl;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void addStep(Step step){
        steps.add(step);
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<User> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void setFavoriteUsers(List<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public void addFavoriteUser(User user){
        favoriteUsers.add(user);
    }

    public void removeFavoriteUser(User user){
        favoriteUsers.remove(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum Category{
        BREAKFAST("Breakfast"),
        LUNCH("Lunch"),
        DINNER("Dinner"),
        DESSERT("Dessert");

        private String name;

        Category(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
