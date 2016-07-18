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
    public Section getSectionById(int id) {
        return sectionRepository.getSectionById(id);
    }
    
    @Override
    public Section getSectionByName(String name) {
        return sectionRepository.getSectionByName(name);
    }
    
    @Override
    public List<Section> getAllSection() {
        return sectionRepository.getAllSection();
    }
    
}
