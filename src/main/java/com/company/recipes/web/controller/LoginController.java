package com.company.recipes.web.controller;

import com.company.recipes.model.User;
import com.company.recipes.services.RoleService;
import com.company.recipes.services.UserService;
import com.company.recipes.web.FlashMessage;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        try {
            Object flash;
            if(!model.containsAttribute("flash")){
                flash = request.getSession().getAttribute("flash");
                model.addAttribute("flash", flash);
            }
        }catch(Exception e){

        }

        return "user/login";
    }

    @RequestMapping(path = "/sign-up", method = RequestMethod.GET)
    public String signupForm(Model model){
        if(!model.containsAttribute("user")){
            model.addAttribute("user", new User());
        }
        return "user/signup";
    }

    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public String registerNewUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("One or more fields have invalid input. Please try again!", FlashMessage.Status.FAILURE));
            return "redirect:/sign-up";
        }

        if(!user.getPassword().equals(user.getMatchingPassword())){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("The passwords do not match. Please try again!", FlashMessage.Status.FAILURE));
            return "redirect:/sign-up";
        }

        user.encryptPasswords();
        user.setRole(roleService.findOne(1L));
        user.setEnabled(true);

        try {
            userService.save(user);
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage(String.format("The username '%s' already exists",
                            user.getUsername()), FlashMessage.Status.FAILURE));
            return "redirect:/sign-up";
        }

        redirectAttributes.addFlashAttribute("flash",
                new FlashMessage("The user has been successfully registered!", FlashMessage.Status.SUCCESS));
        return "redirect:/login";
    }

}
