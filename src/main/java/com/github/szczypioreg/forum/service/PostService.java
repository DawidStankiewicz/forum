/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.User;

public interface PostService {
    
    Post findOne(int id);
    
    List<Post> findAll();
    
    List<Post> findRecent();
    
    List<Post> findRecent(int count);
    
    List<Post> findByUser(User user);
    
    List<Post> findByTopic(int idTopic);
    
    List<Post> findByTopic(Topic topic);
    
    void save(Post post);
    
    void delete(int id);
    
    void delete(Post post);

    void save(String content, String username, int idTopic);
    
}
