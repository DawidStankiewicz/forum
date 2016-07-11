/**
 * Created by Dawid Stankiewicz on 10.07.2016
 */
package com.github.szczypioreg.forum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/users")
    public String allUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @RequestMapping(value = "/regiristration", method = RequestMethod.GET)
    public String regiristrationForm(Model model) {
        User user = new User();
        model.addAttribute("userModel", user);
        return "regiristration";
    }
    
    @RequestMapping(value = "/regiristration", method = RequestMethod.POST)
    public String regiristrationNewUser(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/user/" + user.getName();
    }
    
    @RequestMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            
        }
        return "redirect:/login?logout=true";
    }
    
}
