package com.company.recipes.services;

import com.company.recipes.dao.StepDao;
import com.company.recipes.model.Step;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;
@RunWith(MockitoJUnitRunner.class)
public class StepServiceTest {
    @Mock
    private StepDao dao;

    @InjectMocks
    private StepService service = new StepServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception{
        List<Step> steps = Arrays.asList(
                new Step("Description 1"),
                new Step("Description 2")
        );

        when(dao.findAll()).thenReturn(steps);
        assertEquals("findAll should return two steps", 2,
                    service.findAll().size());
        verify(dao).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne() throws Exception{
        when(dao.findOne(1L)).thenReturn(new Step("Description 1"));
        assertThat(service.findOne(1L), instanceOf(Step.class));
        verify(dao).findOne(1L);
    }
}
