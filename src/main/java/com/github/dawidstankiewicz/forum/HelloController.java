package com.github.dawidstankiewicz.forum;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HelloController {

    @Autowired private UserService userService;

    @GetMapping(value = {"/hello", "/welcome"})
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/hello")
    public String loginOrRegister(@ModelAttribute("email") String email,
                                  RedirectAttributes model) {
        User user = userService.findByEmail(email);
        model.addFlashAttribute("email", email);

        if (user == null) {
            model.addAttribute("userRegistrationForm", new UserRegistrationForm());
            return Routes.redirectNewUserFormPage();
        }
        model.addFlashAttribute("username", user.getUsername());
        return Routes.redirectLoginPage();
    }

}
