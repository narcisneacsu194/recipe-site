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
public interface IngredientDao extends PagingAndSortingRepository<Ingredient, Long> {
    @Query("select i from Ingredient i")
    List<Ingredient> findAll();

    @RestResource(rel = "item-containing", path = "containsItem")
    Page<Ingredient> findByItemContaining(@Param("item") String item, Pageable page);

    @RestResource(rel = "condition-containing", path = "containsCondition")
    Page<Ingredient> findByConditionContaining(@Param("condition") String condition, Pageable page);

}
