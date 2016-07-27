/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.Topic;

public interface TopicService {
    
    List<Topic> findAll();
    
    Topic findOne(int id);
    
    List<Topic> findRecent();
    
    List<Topic> findRecent(int count);
    
    List<Topic> findBySection(Section section);
    
    List<Topic> findBySection(String sectionName);
    
    Topic save(Topic topic);
    
    void delete(int id);
    
    void delete(Topic topic);
    
}
