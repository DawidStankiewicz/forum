package com.github.dawidstankiewicz.forum.section;

import com.github.dawidstankiewicz.forum.model.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    Section findByName(String name);
    
}
