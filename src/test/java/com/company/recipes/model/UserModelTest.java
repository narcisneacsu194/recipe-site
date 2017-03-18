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
    public void setId_ShouldSetIdForUser() throws Exception{
        user.setId(1L);
        Assert.assertEquals((Long)1L, user.getId());
    }

    @Test
    public void setUsername_ShouldSetUsernameForUser() throws Exception{
        user.setUsername("username 1");
        Assert.assertEquals("username 1", user.getUsername());
    }

    @Test
    public void setPassword_ShouldSetPasswordForUser() throws Exception{
        user.setPassword("password 1");
        Assert.assertEquals("password 1", user.getPassword());
    }

    @Test
    public void setMatchingPassword_ShouldSetMatchingPasswordForUser() throws Exception{
        user.setMatchingPassword("matching password 1");
        Assert.assertEquals("matching password 1", user.getMatchingPassword());
    }

    @Test
    public void encryptPasswords_shouldEncryptMainPasswordVariable() throws Exception{
        user.setPassword("password");
        user.setMatchingPassword("password");
        user.encryptPasswords();
        Assert.assertNotEquals("password", user.getPassword());
    }

    @Test
    public void encryptPasswords_shouldEncryptMatchingPasswordVariable() throws Exception{
        user.setPassword("password");
        user.setMatchingPassword("password");
        user.encryptPasswords();
        Assert.assertNotEquals("password", user.getMatchingPassword());
    }

    @Test
    public void setEnabled_ShouldSetAvailabilityForUser() throws Exception{
        user.setEnabled(true);
        Assert.assertEquals(true, user.isEnabled());
    }

    @Test
    public void isAccountNonExpired_ShouldReturnTrue() throws Exception{
        Assert.assertEquals(true, user.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked_ShouldReturnTrue() throws Exception{
        Assert.assertEquals(true, user.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired_ShouldReturnTrue() throws Exception{
        Assert.assertEquals(true, user.isCredentialsNonExpired());
    }

}
