/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.repository.TopicRepository;
import com.github.szczypioreg.forum.service.SectionService;
import com.github.szczypioreg.forum.service.TopicService;

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
    public List<Topic> findRecent() {
        return findRecent(10);
    }
    
    @Override
    public List<Topic> findRecent(int count) {
        // TODO
        return findAll();
    }
    
    @Override
    public List<Topic> findBySection(Section section) {
        return topicRepository.findBySection(section);
    }
    
    @Override
    public List<Topic> findBySection(String sectionName) {
        return findBySection(sectionService.findByName(sectionName));
    }
    
    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
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
