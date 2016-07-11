/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.UserRepository;

@Repository
@Transactional(readOnly = false)
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<User> getAllUsers() {
        return null;
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
