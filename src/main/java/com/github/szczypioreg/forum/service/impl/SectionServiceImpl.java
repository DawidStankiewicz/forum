/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.repository.SectionRepository;
import com.github.szczypioreg.forum.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {
    
    @Autowired
    private SectionRepository sectionRepository;
    
    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }
    
    @Override
    public Section findOne(int id) {
        return sectionRepository.findOne(id);
    }
    
    @Override
    public Section findByName(String name) {
        return sectionRepository.findByName(name);
    }
    
    @Override
    public Section save(Section section) {
        return sectionRepository.save(section);
    }
    
    @Override
    public void delete(int id) {
        delete(findOne(id));
    }
    
    @Override
    public void delete(Section section) {
        sectionRepository.delete(section);
    }
    
}
