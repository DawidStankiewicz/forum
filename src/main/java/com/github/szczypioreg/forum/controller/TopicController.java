/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.szczypioreg.forum.service.PostService;

@Controller
public class TopicController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value = "/section/{section}/topic/{idTopic}", method = RequestMethod.GET)
    public String getTopicsFromSection(@PathVariable int idTopic, Model model) {
        model.addAttribute("posts", postService.findByTopic(idTopic));
        return "topic";
    }
    
    @RequestMapping(value = "/section/{section}/topic/{idTopic}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute String content, Authentication authentication,
            @PathVariable int idTopic, Model model) {
        
        return "topic";
    }
}
