package com.company.recipes.model;

import com.company.recipes.enums.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends BaseEntity{

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull
    private Category category;

    @Min(value = 1)
    private Integer prepTimeHour;

    @Min(value = 2)
    @Max(value = 59)
    private Integer prepTimeMinute;

    @Min(value = 1)
    private Integer cookTimeHour;

    @Min(value = 2)
    @Max(value = 59)
    private Integer cookTimeMinute;

    @Valid
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @Valid
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
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
                  Integer prepTimeHour, Integer prepTimeMinute,
                  Integer cookTimeHour, Integer cookTimeMinute, String photoUrl){
        this();
        this.name = name;
        this.description = description;
        this.category = category;
        this.prepTimeHour = prepTimeHour;
        this.prepTimeMinute = prepTimeMinute;
        this.cookTimeHour = cookTimeHour;
        this.cookTimeMinute = cookTimeMinute;
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

    public Integer getPrepTimeHour() {
        return prepTimeHour;
    }

    public void setPrepTimeHour(Integer prepTimeHour) {
        this.prepTimeHour = prepTimeHour;
    }

    public Integer getCookTimeHour() {
        return cookTimeHour;
    }

    public void setCookTimeHour(Integer cookTimeHour) {
        this.cookTimeHour = cookTimeHour;
    }

    public Integer getPrepTimeMinute() {
        return prepTimeMinute;
    }

    public void setPrepTimeMinute(Integer prepTimeMinute) {
        this.prepTimeMinute = prepTimeMinute;
    }

    public Integer getCookTimeMinute() {
        return cookTimeMinute;
    }

    public void setCookTimeMinute(Integer cookTimeMinute) {
        this.cookTimeMinute = cookTimeMinute;
    }
}
