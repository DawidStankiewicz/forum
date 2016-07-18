/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.repository.TopicRepository;
import com.github.szczypioreg.forum.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.getAllTopics();
    }
    
    @Override
    public Topic getTopicdById(int id) {
        return topicRepository.getTopicdById(id);
    }
    
    @Override
    public List<Topic> getRecent() {
        return getRecent(10);
    }
    
    @Override
    public List<Topic> getRecent(int count) {
        return topicRepository.getRecent(count);
    }

    @Override
    public void create(Topic topic) {
        topicRepository.create(topic);
    }
    
   
    
}
