package com.company.recipes.dao;

import com.company.recipes.model.Step;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepDao extends CrudRepository<Step, Long>{
}
