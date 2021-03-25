package com.github.dawidstankiewicz.forum.topic;

import com.github.dawidstankiewicz.forum.model.entity.Topic;
import com.github.dawidstankiewicz.forum.section.SectionService;
import com.github.dawidstankiewicz.forum.model.entity.User;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dawidstankiewicz.forum.model.entity.Section;


@Service
public class TopicService {
    
    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private SectionService sectionService;
    
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
    
    public Topic findOne(int id) {
        // todo fix Optional
        return topicRepository.findById(id).get();
    }
    
    public Set<Topic> findRecent() {
        return topicRepository.findTop5ByOrderByCreationDateDesc();
    }

    public Set<Topic> findAllByOrderByCreationDateDesc() {
        return topicRepository.findAllByOrderByCreationDateDesc();
    }

    public Set<Topic> findBySection(Section section) {
        return topicRepository.findBySection(section);
    }

    public Set<Topic> findBySection(String sectionName) {
        return findBySection(sectionService.findByName(sectionName));
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }
    
    public Set<Topic> findBySection(int id) {
        return findBySection(sectionService.findOne(id));
    }

    public Set<Topic> findByUser(User user) {
        return topicRepository.findByUser(user);
    }

    public void delete(int id) {
        delete(findOne(id));
    }
    
    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }
    
}
