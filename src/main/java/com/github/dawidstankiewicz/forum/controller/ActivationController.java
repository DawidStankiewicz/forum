package com.github.dawidstankiewicz.forum.controller;

import com.github.dawidstankiewicz.forum.entity.ActivationCode;
import com.github.dawidstankiewicz.forum.service.ActivationCodeService;
import com.github.dawidstankiewicz.forum.service.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */

@Controller
public class ActivationController {

    @Autowired
    private ActivationService activationService;

    @RequestMapping(value = "/users/{username}/activation")
    public String activateUserAndRedirectToLoginPage(@PathVariable String username,
        @RequestParam String id) {
        activationService.activate(username, id);
        return "redirect:~/login";
    }
}
