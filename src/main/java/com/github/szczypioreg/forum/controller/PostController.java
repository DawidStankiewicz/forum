/**
 * Created by Dawid Stankiewicz on 23.07.2016
 */
package com.github.szczypioreg.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.service.PostService;


@Controller
@RequestMapping(value = "/post")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, Authentication authentication) {
        Post post = postService.findOne(id);
        if (post.getUser().getUsername().equals(authentication.getName())) {
            postService.delete(id);
            return "redirect: /forum/topic/" + post.getTopic().getIdTopic();
        }
        return "redirect: /forum/";
    }
}
