/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.User;


public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
    List<Topic> findBySection(Section section);
    
    Set<Topic> findByUser(User user);
    
}
