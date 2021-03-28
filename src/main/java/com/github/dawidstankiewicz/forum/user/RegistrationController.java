package com.github.dawidstankiewicz.forum.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping(value = "/new-user")
    public String userRegistrationForm(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "new_user_form";
    }

    @PostMapping(value = "/new-user")
    public String registerNewUser(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "new_user_form";
    }
}
