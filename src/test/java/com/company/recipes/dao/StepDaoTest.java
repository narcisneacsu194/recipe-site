package com.company.recipes.dao;

import com.company.recipes.model.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = "spring.datasource.url = jdbc:mysql://localhost:3306/test_StepDaoTest_steps")
public class StepDaoTest {
    @Autowired
    private StepDao dao;

    @After
    public void deleteNewSteps(){
        List<Step> steps = dao.findAll();

        steps.forEach(step -> {
            if(step.getDescription().startsWith("New")){
                dao.delete(step.getId());
            }
        });
    }

    @Test
    public void findAll_ShouldReturn30Steps(){
        Assert.assertThat(dao.findAll(), hasSize(30));
    }

    @Test
    public void findOne_ShouldReturnNull(){
        Assert.assertThat(dao.findOne(31L), nullValue(Step.class));
    }

    @Test
    public void save_ShouldPersistEntity(){
        Step step = new Step("New Step 1");
        dao.save(step);
        Assert.assertThat(dao.findOne(step.getId()), notNullValue(Step.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities(){
        List<Step> steps = new ArrayList<>();
        Step step = new Step("New Step 1");
        steps.add(step);
        Step step2 = new Step("New Step 2");
        steps.add(step2);
        Step step3 = new Step("New Step 3");
        steps.add(step3);

        dao.save(steps);

        Assert.assertThat(dao.findAll(), hasSize(33));
    }

    @Test
    public void delete_ShouldDeleteEntity(){
        Step step = new Step("New Step 1");
        dao.save(step);
        Long stepId = step.getId();
        dao.delete(step);
        Assert.assertThat(dao.findOne(stepId), nullValue(Step.class));
    }
}
