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

import com.github.szczypioreg.forum.domain.Topic;
import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.TopicRepository;

@Repository
@Transactional
public class TopicRepositoryImpl implements TopicRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Topic> getAllTopics() {
        return entityManager.createQuery("select t from Topic t").getResultList();
    }
    
    @Override
    public Topic getTopicdById(int id) {
        return entityManager.find(Topic.class, id);
    }
    
    @Override
    public List<Topic> getRecent(int count) {
        return (List<Topic>) entityManager
                .createQuery("select t from Topic t order by t.idTopic desc").setMaxResults(count)
                .getResultList();
    }
    
    @Override
    public void create(Topic topic) {
        entityManager.persist(topic);
    }
    
}
