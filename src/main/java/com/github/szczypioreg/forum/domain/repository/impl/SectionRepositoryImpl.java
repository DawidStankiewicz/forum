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
    public Section getSectionById(int id) {
        return entityManager.find(Section.class, id);
    }
    
    @Override
    public Section getSectionByName(String name) {
        return (Section) entityManager.createQuery("select s from Section where name=?")
                .setParameter(0, name).getSingleResult();
    }
    
    @Override
    public List<Section> getAllSection() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Section> criteriaQuery = criteriaBuilder.createQuery(Section.class);
        Root<Section> rootEntry = criteriaQuery.from(Section.class);
        CriteriaQuery<Section> all = criteriaQuery.select(rootEntry);
        return entityManager.createQuery(all).getResultList();
    }
    
}
