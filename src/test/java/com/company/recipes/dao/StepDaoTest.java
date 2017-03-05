package com.company.recipes.dao;

import com.company.recipes.Application;
import com.company.recipes.model.Step;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:steps.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class StepDaoTest {
    @Autowired
    private StepDao dao;

    @Test
    public void findAll_ShouldReturnThree() throws Exception{
        Assert.assertThat(dao.findAll(), hasSize(3));
    }

    @Test
    public void findOne_ShouldReturnNull() throws Exception{
        Assert.assertThat(dao.findOne(5L), nullValue(Step.class));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception{
        Step step = new Step("Description 1");
        dao.save(step);
        Assert.assertThat(dao.findOne(step.getId()), notNullValue(Step.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities() throws Exception{
        List<Step> steps = new ArrayList<>();
        Step step = new Step("Description 1");
        steps.add(step);
        Step step2 = new Step("Description 2");
        steps.add(step2);
        Step step3 = new Step("Description 3");
        steps.add(step3);

        dao.save(steps);

        Assert.assertThat(dao.findAll(), hasSize(6));
    }

    @Test
    public void delete_ShouldDeleteEntity() throws Exception{
        Step step = new Step("Description 1");
        dao.save(step);
        Long stepId = step.getId();
        dao.delete(step);
        Assert.assertThat(dao.findOne(stepId), nullValue(Step.class));
    }
}
