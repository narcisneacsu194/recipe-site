package com.company.recipes.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails{
    public static final PasswordEncoder PASSWORD_ENCODER =
            new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 20)
    private String username;

    @NotNull
    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "favoriteUsers")
    private List<Recipe> favoritedRecipes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recipe> ownedRecipes = new ArrayList<>();

    public User(){
        id = null;
        username = null;
        password = null;
        role = null;
    }

    public User(String username, boolean enabled, String password){
        this();
        this.username = username;
        this.enabled = enabled;
        setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Recipe> getFavoritedRecipes() {
        return favoritedRecipes;
    }

    public void setFavoritedRecipes(List<Recipe> favoritedRecipes) {
        this.favoritedRecipes = favoritedRecipes;
    }

    public void addFavoritedRecipe(Recipe recipe){
        favoritedRecipes.add(recipe);
    }

    public void removeFavoritedRecipe(Recipe recipe){
        favoritedRecipes.remove(recipe);
    }

    public List<Recipe> getOwnedRecipes() {
        return ownedRecipes;
    }

    public void setOwnedRecipes(List<Recipe> ownedRecipes) {
        this.ownedRecipes = ownedRecipes;
    }

    public void addOwnedRecipe(Recipe recipe){
        ownedRecipes.add(recipe);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}