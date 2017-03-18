package com.company.recipes.dao;

import com.company.recipes.model.User;
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
                properties = "spring.datasource.url = jdbc:h2:./database/user-UserDaoTest-users;DB_CLOSE_ON_EXIT=FALSE")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAll_ShouldReturnThree() throws Exception{
        Assert.assertThat(userDao.findAll(), hasSize(3));
    }

    @Test
    public void findOne_ShouldReturnNull() throws Exception{
        Assert.assertThat(userDao.findOne(4L), nullValue(User.class));
    }

    @Test
    public void findOne_ShouldReturnSpecificUser() throws Exception{
        Assert.assertThat(userDao.findOne(2L), notNullValue(User.class));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception{
        User user = new User("username1", true, "password", "password");
        userDao.save(user);
        Assert.assertThat(userDao.findOne(3L), notNullValue(User.class));
    }

    @Test
    public void save_ShouldPersistMultipleEntities() throws Exception{
        List<User> users = Arrays.asList(
                new User("username2", true, "password1", "password1"),
                new User("username3", true, "password2", "password2"),
                new User("username4", true, "password3", "password3")
        );

        userDao.save(users);

        Assert.assertThat(userDao.findAll(), hasSize(6));
    }

    @Test
    public void delete_ShouldDeleteEntity() throws Exception{
        User user5 = new User("username5", true, "password5", "password5");
        userDao.save(user5);
        Long userId = user5.getId();
        userDao.delete(userId);
        Assert.assertThat(userDao.findOne(7L), nullValue(User.class));
    }

}
