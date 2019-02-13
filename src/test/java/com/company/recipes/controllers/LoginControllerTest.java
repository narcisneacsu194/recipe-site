package com.company.recipes.controllers;

import com.company.recipes.model.Role;
import com.company.recipes.model.User;
import com.company.recipes.services.RoleService;
import com.company.recipes.services.UserService;
import com.company.recipes.web.FlashMessage;
import com.company.recipes.web.controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class LoginControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        LoginController loginController =
                new LoginController(userService, roleService);

        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testSignupForm() throws Exception{
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/signup"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterNewUserSuccessfully() throws Exception{

        when(roleService.findOne(anyLong()))
                .thenReturn(new Role("ROLE_USER"));

        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "password")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(flash().attributeExists("flash"));

        verify(roleService, times(1)).findOne(anyLong());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterNewUserUsingNullUsername() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("password", "password")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingNullPassword() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingNullMatchingPassword() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingEmptyStringForUsername() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "")
                .param("password", "password")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingEmptyStringForPassword() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingEmptyStringForMatchingPassword() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "password")
                .param("matchingPassword", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserUsingTwoDifferentPasswords() throws Exception{
        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user")
                .param("password", "password")
                .param("matchingPassword", "password2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));
    }

    @Test
    public void testRegisterNewUserCatchBlock() throws Exception{

        when(roleService.findOne(anyLong()))
                .thenReturn(new Role("ROLE_USER"));
        doThrow(Exception.class)
                .when(userService)
                .save(any(User.class));

        mockMvc.perform(post("/sign-up")
                .param("username", "user")
                .param("password", "password")
                .param("matchingPassword", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sign-up"))
                .andExpect(flash().attributeExists("user"))
                .andExpect(flash().attributeExists("flash"));


        verify(roleService, times(1)).findOne(anyLong());
        verify(userService, times(1)).save(any(User.class));
    }
}
