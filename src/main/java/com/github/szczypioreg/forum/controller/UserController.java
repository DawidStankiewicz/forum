/**
 * Created by Dawid Stankiewicz on 10.07.2016
 */
package com.github.szczypioreg.forum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.szczypioreg.forum.controller.form.NewUserForm;
import com.github.szczypioreg.forum.controller.model.UserProfile;
import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.exception.UserNotFoundException;
import com.github.szczypioreg.forum.service.UserService;
import com.github.szczypioreg.forum.service.model.UserProfileService;


@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserProfileService userProfileService;
    
    @RequestMapping(value = "/user/{username}")
    public String findUserByUsernameAndViewProfilePage(@PathVariable("username") String username,
            Model model) {
        UserProfile userProfile;
        try {
            userProfile = userProfileService.findOne(username);
        } catch (NullPointerException e) {
            throw new UserNotFoundException();
        }
        model.addAttribute("userProfile", userProfile);
        return "user";
    }
    
    @RequestMapping(value = "/user/id/{id}")
    public String findUserByIdAndViewProfilePage(@PathVariable("id") int id, Model model) {
        return "redirect:/user/" + userService.findOne(id).getUsername();
    }
    
    @RequestMapping(value = "/users")
    public String listOfAllUser(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String regiristrationPage(Model model) {
        model.addAttribute("newUser", new NewUserForm());
        return "new_user_form";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processAndSaveNewUser(@Valid @ModelAttribute("newUser") NewUserForm newUser,
            BindingResult result, RedirectAttributes model) {
        
        if (result.hasErrors()) {
            return "new_user_form";
        }
        
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        
        userService.save(user);
        
        model.addFlashAttribute("message", "user.successfully.added");
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/logout")
    public String logOutAndRedirectToLoginPage(HttpServletRequest request,
            HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
    
}
