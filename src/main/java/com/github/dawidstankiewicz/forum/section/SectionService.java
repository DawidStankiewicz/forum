package com.github.dawidstankiewicz.forum.section;

import com.github.dawidstankiewicz.forum.model.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

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

    public Page<Section> findSections(Pageable pageable) {
        return sectionRepository.findAll(pageable);
    }
}
