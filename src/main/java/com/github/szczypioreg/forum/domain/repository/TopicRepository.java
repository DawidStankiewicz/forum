/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
    List<Topic> findBySection(Section section);
    
}
