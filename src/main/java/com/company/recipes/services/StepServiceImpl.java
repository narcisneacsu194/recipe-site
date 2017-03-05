package com.company.recipes.services;

import com.company.recipes.dao.StepDao;
import com.company.recipes.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepServiceImpl implements StepService{

    @Autowired
    private StepDao stepDao;

    @Override
    @SuppressWarnings("unchecked")
    public List<Step> findAll() {
        return (List)stepDao.findAll();
    }

    @Override
    public Step findOne(Long id) {
        return stepDao.findOne(id);
    }

    @Override
    public void save(Step step) {
        stepDao.save(step);
    }

    @Override
    public void save(List<Step> steps) {
        stepDao.save(steps);
    }

    @Override
    public void delete(Step step) {
        stepDao.delete(step);
    }
}
