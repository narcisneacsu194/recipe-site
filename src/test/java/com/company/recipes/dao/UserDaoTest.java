package com.company.recipes.dao;

import com.company.recipes.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = "spring.datasource.url = jdbc:mysql://localhost:3306/user_UserDaoTest_users")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @After
    public void deleteNewUsers(){
        List<User> users = userDao.findAll();

        users.forEach(user -> {
            if(user.getUsername().startsWith("New")){
                userDao.delete(user.getId());
            }
        });
    }

    @Test
    public void findAll_ShouldReturnTwoUsers(){
        Assert.assertThat(userDao.findAll(), hasSize(2));
    }

    @Test
    public void findOne_ShouldReturnNull(){
        Assert.assertThat(userDao.findOne(3L), nullValue(User.class));
    }

    @Test
    public void findOne_ShouldReturnSpecificUser(){
        Assert.assertThat(userDao.findOne(2L), notNullValue(User.class));
    }

    @Test
    public void save_ShouldPersistEntity(){
        User user = new User("NewUser1", true, "password", "password");
        userDao.save(user);
        Assert.assertThat(userDao.findOne(3L), notNullValue(User.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities(){
        List<User> users = Arrays.asList(
                new User("NewUser2", true, "password1", "password1"),
                new User("NewUser3", true, "password2", "password2"),
                new User("NewUser4", true, "password3", "password3")
        );

        userDao.save(users);

        Assert.assertThat(userDao.findAll(), hasSize(5));
    }

    @Test
    public void delete_ShouldDeleteEntity(){
        User user5 = new User("NewUser5", true, "password5", "password5");
        userDao.save(user5);
        Long userId = user5.getId();
        userDao.delete(userId);
        Assert.assertThat(userDao.findOne(7L), nullValue(User.class));
    }

}
