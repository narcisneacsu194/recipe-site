package com.company.recipes.dao;

import com.company.recipes.enums.Category;
import com.company.recipes.model.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDao extends CrudRepository<Recipe, Long> {
    @Query("select r from Recipe r")
    List<Recipe> findAll();

    @Query("select r from Recipe r where r.user.id=:#{principal.id}")
    List<Recipe> findAllFromSpecificUser();

    Recipe findOne(@Param("id") Long id);

    @Query("select r from Recipe r where r.description like %?1%")
    List<Recipe> findByDescriptionContaining(String descriptionChunk);

    @Query("select r from Recipe r where r.category = ?1")
    List<Recipe> findByCategory(Category category);
}
