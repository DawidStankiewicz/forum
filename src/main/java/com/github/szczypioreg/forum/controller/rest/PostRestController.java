/**
 * Created by Dawid Stankiewicz on 23.07.2016
 */
package com.github.szczypioreg.forum.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.repository.PostRepository;

@RestController
@RequestMapping(value = "/post/")
public class PostRestController {
    
    @Autowired
    private PostRepository postRepository;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable(value = "id") int id) {
        postRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable(value = "id") int id) {
        return postRepository.findOne(id);
    }
    
}
