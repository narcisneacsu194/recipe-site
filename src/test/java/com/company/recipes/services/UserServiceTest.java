package com.company.recipes.services;

import com.company.recipes.dao.UserDao;
import com.company.recipes.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

    @Test
    public void loadUserByUsername_ShouldReturnOne(){
        User user = new User();
        user.setUsername("user3");
        when(userDao.findByUsername(anyString())).thenReturn(user);
        User returnedUser = (User) userService.loadUserByUsername(user.getUsername());
        Assert.assertEquals("loadUserByUsername doesn't return the correct recipe.",
                user, returnedUser);
        verify(userDao, times(1)).findByUsername(anyString());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_ShouldThrowException(){
        User user = null;
        when(userDao.findByUsername(anyString())).thenReturn(user);
        userService.loadUserByUsername("user3");
    }

    @Test
    public void save_ShouldCallDaoSaveMethodOnce(){
        User user = new User();
        user.setUsername("user3");
        userService.save(user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    public void registerNewUser_ShouldReturnUser(){
        User user = new User("user3", true,
                "password", "password");
        when(userDao.save(Mockito.any(User.class))).thenReturn(user);
        User returnedUser = userService.registerNewUser(user.getUsername(),
                user.isEnabled(), user.getPassword(), user.getMatchingPassword());
        Assert.assertEquals("registerNewUser doesn't return the correct recipe.", user,
                returnedUser);
        verify(userDao, times(1)).save(Mockito.any(User.class));
    }
}
