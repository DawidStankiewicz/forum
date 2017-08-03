/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.dawidstankiewicz.forum.entity.repository;

import com.github.dawidstankiewicz.forum.entity.Topic;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.dawidstankiewicz.forum.entity.Post;
import com.github.dawidstankiewicz.forum.entity.User;


public interface PostRepository extends JpaRepository<Post, Integer> {
    
    Set<Post> findByUser(User user);
    
    Set<Post> findByTopic(Topic topic);
    
    Set<Post> findAllByOrderByCreationDateDesc();
    
    Set<Post> findTop5ByOrderByCreationDateDesc();
}
