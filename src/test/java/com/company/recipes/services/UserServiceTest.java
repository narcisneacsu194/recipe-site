package com.company.recipes.services;

import com.company.recipes.dao.UserDao;
import com.company.recipes.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo(){
        List<User> users = Arrays.asList(
                new User("NewUser1", true, "password1", "password1"),
                new User("NewUser2", true, "password2", "password2")
        );

        when(userDao.findAll()).thenReturn(users);

        Assert.assertEquals("findAll should return two users", 2,
                userService.findAll().size());

        verify(userDao, times(1)).findAll();
    }

    @Test
    public void findOne_ShouldReturnOne(){
        User user = new User("NewUser1", true, "password1", "password1");
        when(userDao.findByUsername(user.getUsername())).thenReturn(user);

        Assert.assertThat(userService.findByUsername(user.getUsername()), instanceOf(User.class));

        verify(userDao, times(1)).findByUsername(user.getUsername());
    }
}
