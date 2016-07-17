/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(rootEntry);
        return entityManager.createQuery(all).getResultList();
    }
    
    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
    
}
