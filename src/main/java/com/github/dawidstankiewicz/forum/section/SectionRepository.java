/**
 * Created by Dawid Stankiewicz on 17.07.2016
 */
package com.github.dawidstankiewicz.forum.section;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.dawidstankiewicz.forum.section.Section;


public interface SectionRepository extends JpaRepository<Section, Integer> {
    
    Section findByName(String name);
    
}
