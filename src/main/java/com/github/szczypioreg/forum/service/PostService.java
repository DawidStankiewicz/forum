/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.User;

public interface PostService {
    
    Post getPostById(int id);
    
    List<Post> getRecent();
    
    List<Post> getRecent(int count);
    
    List<Post> getPostsByUser(User user);
    
    void create(Post post);
    
    List<Post> getPostsByTopic(int idTopic);
    
}
