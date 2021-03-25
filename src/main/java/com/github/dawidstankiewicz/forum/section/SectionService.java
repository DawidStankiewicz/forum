package com.github.dawidstankiewicz.forum.section;

import java.util.List;

import com.github.dawidstankiewicz.forum.model.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findOne(int id) {
        return sectionRepository.findById(id).get();
    }

    public Section findByName(String name) {
        return sectionRepository.findByName(name);
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public void delete(int id) {
        delete(findOne(id));
    }

    public void delete(Section section) {
        sectionRepository.delete(section);
    }

}
