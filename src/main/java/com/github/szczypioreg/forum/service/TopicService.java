/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;
import java.util.Set;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.User;


public interface TopicService {
    
    List<Topic> findAll();
    
    Topic findOne(int id);
    
    List<Topic> findRecent();
    
    List<Topic> findRecent(int count);
    
    List<Topic> findBySection(Section section);
    
    List<Topic> findBySection(String sectionName);
    
    Topic save(Topic topic);
    
    List<Topic> findBySection(int id);
    
    Set<Topic> findByUser(User user);
    
    void delete(int id);
    
    void delete(Topic topic);
    
}
