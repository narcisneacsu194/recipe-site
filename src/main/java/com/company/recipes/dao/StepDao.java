package com.company.recipes.dao;

import com.company.recipes.model.Step;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepDao extends CrudRepository<Step, Long> {
    @Query("select s from Step s")
    List<Step> findAll();
}
