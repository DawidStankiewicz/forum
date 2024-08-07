package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.config.Routes;
import com.github.dawidstankiewicz.forum.model.dto.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {

    private final UserRegistrationService registrationService;

    public RegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/new-user")
    public String userRegistrationForm(Model model, String error) {
        log.info("User registration form");
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return Routes.NEW_USER_FORM;
    }

    @PostMapping(value = "/new-user")
    public String registerNewUser(@Valid UserRegistrationForm registrationForm,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return Routes.NEW_USER_FORM;
        }
        User created = registrationService.registerUser(registrationForm);
        model.addAttribute("username", created.getUsername());
        return Routes.REGISTRATION_CONFIRMATION;
    }
}
