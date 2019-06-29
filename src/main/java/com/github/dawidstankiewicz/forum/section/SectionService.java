/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.dawidstankiewicz.forum.section;

import java.util.List;

import com.github.dawidstankiewicz.forum.section.Section;


public interface SectionService {
    
    List<Section> findAll();
    
    Section findOne(int id);
    
    Section findByName(String name);
    
    Section save(Section section);
    
    void delete(int id);
    
    void delete(Section section);
    
}
