/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.PostRepository;
import com.github.szczypioreg.forum.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Override
    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }
    
    @Override
    public List<Post> getRecent() {
        return getRecent(10);
    }
    
    @Override
    public List<Post> getRecent(int count) {
        return postRepository.getRecent(count);
    }
    
    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.getPostsByUser(user);
    }
    
    @Override
    public void create(Post post) {
        postRepository.create(post);
    }
    
}
