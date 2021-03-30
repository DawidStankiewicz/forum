package com.github.dawidstankiewicz.forum.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController {

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            log.info("Logging in failed: " + error);
            model.addAttribute("error", error);
        }

        if (logout != null) {
            model.addAttribute("message", "login.logout");
        }

        return "login";
    }
}
