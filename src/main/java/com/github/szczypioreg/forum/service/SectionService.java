/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.List;

import com.github.szczypioreg.forum.domain.Section;

public interface SectionService {
    
    List<Section> getAllSections();
    
    Section getSectionById(int id);
    
    Section getSectionByName(String name);
    
    void create(Section section);
    
    void delete(int id);
    
}
