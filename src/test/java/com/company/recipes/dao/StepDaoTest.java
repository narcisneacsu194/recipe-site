package com.company.recipes.dao;

import com.company.recipes.model.Step;
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
                properties = "spring.datasource.url = jdbc:h2:./database/test-StepDaoTest-steps;DB_CLOSE_ON_EXIT=FALSE")
public class StepDaoTest {
    @Autowired
    private StepDao dao;

    @Test
    public void findAll_ShouldReturn694Steps() throws Exception{
        Assert.assertThat(dao.findAll(), hasSize(694));
    }

    @Test
    public void findOne_ShouldReturnNull() throws Exception{
        Assert.assertThat(dao.findOne(695L), nullValue(Step.class));
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

        Assert.assertThat(dao.findAll(), hasSize(697));
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
