/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.Topic;

public interface TopicService {
    
    List<Topic> getAllTopics();
    
    Topic getTopicdById(int id);
    
    List<Topic> getRecent();
    
    List<Topic> getRecent(int count);
    
    void create(Topic topic);
    
    List<Topic> getTopicsBySection(String section);
    
}
