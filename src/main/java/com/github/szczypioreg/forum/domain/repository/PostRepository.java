/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
   
    List<Post> findByUser(User user);
    
    List<Post> findByTopic(Topic topic);
    
}
