/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import com.github.szczypioreg.forum.domain.Topic;

public interface TopicRepository {
    
    List<Topic> getAllTopics();
    
    Topic getTopicdById(int id);
    
    List<Topic> getRecent(int count);
    
    void create(Topic topic);
    
    List<Topic> getTopicsBySection(String section);
    
}
