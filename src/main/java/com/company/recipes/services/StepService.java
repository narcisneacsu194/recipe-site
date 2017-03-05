package com.company.recipes.services;

import com.company.recipes.model.Step;

import java.util.List;

public interface StepService {
    List<Step> findAll();
    Step findOne(Long id);
    void save(Step step);
    void save(List<Step> steps);
    void delete(Step step);
}
