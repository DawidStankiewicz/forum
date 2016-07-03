/**
 * Created by Dawid Stankiewicz on 29 Jun 2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello world!");
        return "home";
    }
}
