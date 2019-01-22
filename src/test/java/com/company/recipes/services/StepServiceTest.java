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

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StepServiceTest {
    @Mock
    private StepDao dao;

    @InjectMocks
    private StepService service = new StepServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo(){
        List<Step> steps = Arrays.asList(
                new Step("New Step 1"),
                new Step("New Step 2")
        );

        when(dao.findAll()).thenReturn(steps);
        assertEquals("findAll should return two steps", 2,
                    service.findAll().size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne(){
        when(dao.findOne(1L)).thenReturn(new Step("New Step 1"));
        assertThat(service.findOne(1L), instanceOf(Step.class));
        verify(dao, times(1)).findOne(anyLong());
    }
}
