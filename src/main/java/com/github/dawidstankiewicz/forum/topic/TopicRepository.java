/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.section.Section;
import com.github.dawidstankiewicz.forum.user.User;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
    Set<Topic> findBySection(Section section);
    
    Set<Topic> findByUser(User user);
    
    Set<Topic> findAllByOrderByCreationDateDesc();
    
    Set<Topic> findTop5ByOrderByCreationDateDesc();
    
    
}
