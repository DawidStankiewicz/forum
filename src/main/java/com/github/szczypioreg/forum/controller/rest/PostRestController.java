/**
 * Created by Dawid Stankiewicz on 23.07.2016
 */
package com.github.szczypioreg.forum.controller.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.service.PostService;

@RestController
@RequestMapping(value="/rest/")
public class PostRestController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Post findOne(@PathVariable(value = "id") int id) {
        return postService.findOne(id);
    }
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public Set<Post> getPostsByTopic(@PathVariable(value = "id") int topicId) {
        return postService.findByTopic(topicId);
    }
    
    @RequestMapping(value = "post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") int id) {
        postService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
