package com.github.dawidstankiewicz.forum.user.activation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ActivationController {

    @Autowired
    private ActivationService activationService;

    @RequestMapping(value = "/users/{username}/activation")
    public String activateUserAndRedirectToLoginPage(@PathVariable String username,
        @RequestParam String id) {
        activationService.activate(username, id);
        return "activation_success";
    }

}
