package com.github.dawidstankiewicz.forum.section;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.dawidstankiewicz.forum.model.entity.Section;


public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    Section findByName(String name);
    
}
