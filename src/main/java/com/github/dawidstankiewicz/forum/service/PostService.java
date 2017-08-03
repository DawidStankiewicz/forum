/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.dawidstankiewicz.forum.service;

import com.github.dawidstankiewicz.forum.entity.Topic;
import com.github.dawidstankiewicz.forum.entity.User;
import java.util.List;
import java.util.Set;

import com.github.dawidstankiewicz.forum.entity.Post;


public interface PostService {
    
    Post findOne(int id);
    
    List<Post> findAll();
    
    Set<Post> findRecent();
    
    Set<Post> findAllByOrderByCreationDateDesc();
    
    Set<Post> findByUser(User user);
    
    Set<Post> findByTopic(int idTopic);
    
    Set<Post> findByTopic(Topic topic);
    
    void save(Post post);
    
    void delete(int id);
    
    void delete(Post post);
    
    void save(String content,
              String username,
              int idTopic);
    
}
