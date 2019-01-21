package com.company.recipes.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class UserModelTest {
    private User user;

    @Before
    public void setUp(){
        user = new User();
    }

    @Test
    public void setId_ShouldSetIdForUser(){
        user.setId(1L);
        Assert.assertEquals((Long)1L, user.getId());
    }

    @Test
    public void setUsername_ShouldSetUsernameForUser(){
        user.setUsername("username 1");
        Assert.assertEquals("username 1", user.getUsername());
    }

    @Test
    public void setPassword_ShouldSetPasswordForUser(){
        user.setPassword("password 1");
        Assert.assertEquals("password 1", user.getPassword());
    }

    @Test
    public void setMatchingPassword_ShouldSetMatchingPasswordForUser(){
        user.setMatchingPassword("matching password 1");
        Assert.assertEquals("matching password 1", user.getMatchingPassword());
    }

    @Test
    public void encryptPasswords_shouldEncryptMainPasswordVariable(){
        user.setPassword("password");
        user.setMatchingPassword("password");
        user.encryptPasswords();
        Assert.assertNotEquals("password", user.getPassword());
    }

    @Test
    public void encryptPasswords_shouldEncryptMatchingPasswordVariable(){
        user.setPassword("password");
        user.setMatchingPassword("password");
        user.encryptPasswords();
        Assert.assertNotEquals("password", user.getMatchingPassword());
    }

    @Test
    public void setEnabled_ShouldSetAvailabilityForUser(){
        user.setEnabled(true);
        Assert.assertTrue(user.isEnabled());
    }

    @Test
    public void isAccountNonExpired_ShouldReturnTrue(){
        Assert.assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked_ShouldReturnTrue(){
        Assert.assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired_ShouldReturnTrue(){
        Assert.assertTrue(user.isCredentialsNonExpired());
    }

}
