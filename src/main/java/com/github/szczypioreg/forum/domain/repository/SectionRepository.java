/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import com.github.szczypioreg.forum.domain.Section;

public interface SectionRepository {
    
    Section getSectionById(int id);
    
    Section getSectionByName(String name);
    
    List<Section> getAllSection();
    
}
