/**
 * Created by Dawid Stankiewicz on 19.07.2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.szczypioreg.forum.service.PostService;

@Controller
public class TopicController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping("/section/{section}/topic/{idTopic}")
    public String getTopicsFromSection(@PathVariable int idTopic, Model model) {
        model.addAttribute("posts", postService.getPostsByTopic(idTopic));
        return "topic";
    }
}
