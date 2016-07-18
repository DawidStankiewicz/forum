/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.szczypioreg.forum.service.SectionService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private SectionService sectionService;
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("sections", sectionService.getAllSection());
        return "home";
    }
}
