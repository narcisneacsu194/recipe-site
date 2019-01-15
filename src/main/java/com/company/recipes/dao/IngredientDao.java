package com.company.recipes.dao;

import com.company.recipes.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDao extends CrudRepository<Ingredient, Long> {
    @Query("select i from Ingredient i")
    List<Ingredient> findAll();

    @RestResource(rel = "description-containing", path = "containsDescription")
    Page<Ingredient> findByDescriptionContaining(@Param("description") String description, Pageable page);
}
