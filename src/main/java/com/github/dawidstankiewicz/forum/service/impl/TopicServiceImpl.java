/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.dawidstankiewicz.forum.service.impl;

import com.github.dawidstankiewicz.forum.entity.Topic;
import com.github.dawidstankiewicz.forum.entity.User;
import com.github.dawidstankiewicz.forum.service.SectionService;
import com.github.dawidstankiewicz.forum.service.TopicService;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dawidstankiewicz.forum.entity.Section;
import com.github.dawidstankiewicz.forum.entity.repository.TopicRepository;


@Service
public class TopicServiceImpl implements TopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private SectionService sectionService;
    
    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
    
    @Override
    public Topic findOne(int id) {
        return topicRepository.findOne(id);
    }
    
    @Override
    public Set<Topic> findRecent() {
        return topicRepository.findTop5ByOrderByCreationDateDesc();
    }
    
    @Override
    public Set<Topic> findAllByOrderByCreationDateDesc() {
        return topicRepository.findAllByOrderByCreationDateDesc();
    }
    
    @Override
    public Set<Topic> findBySection(Section section) {
        return topicRepository.findBySection(section);
    }
    
    @Override
    public Set<Topic> findBySection(String sectionName) {
        return findBySection(sectionService.findByName(sectionName));
    }
    
    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }
    
    @Override
    public Set<Topic> findBySection(int id) {
        return findBySection(sectionService.findOne(id));
    }
    
    @Override
    public Set<Topic> findByUser(User user) {
        return topicRepository.findByUser(user);
    }
    
    @Override
    public void delete(int id) {
        delete(findOne(id));
    }
    
    @Override
    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }
    
}
