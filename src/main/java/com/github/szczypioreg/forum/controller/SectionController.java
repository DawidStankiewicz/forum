/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.szczypioreg.forum.service.TopicService;

@Controller
public class SectionController {
    
    @Autowired
    private TopicService topicService;
    
    @RequestMapping("/section/{section}")
    public String getTopicsFromSection(@PathVariable String section, Model model) {
        model.addAttribute("topics", topicService.getTopicsBySection(section));
        return "section";
    }
}
