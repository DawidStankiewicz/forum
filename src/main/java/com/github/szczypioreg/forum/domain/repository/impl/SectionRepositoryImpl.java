/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szczypioreg.forum.domain.Section;
import com.github.szczypioreg.forum.domain.repository.SectionRepository;

@Repository
@Transactional
public class SectionRepositoryImpl implements SectionRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Section> getAllSections() {
        return entityManager.createQuery("select s from Section s").getResultList();
    }
    
    @Override
    public Section getSectionById(int id) {
        return entityManager.find(Section.class, id);
    }
    
    @Override
    public Section getSectionByName(String name) {
        return (Section) entityManager.createQuery("select s from Section where name=?")
                .setParameter(0, name).getSingleResult();
    }
    
    @Override
    public void create(Section section) {
        entityManager.persist(section);
    }
    
    @Override
    public void delete(int id) {
        entityManager.remove(getSectionById(id));
    }
    
}
