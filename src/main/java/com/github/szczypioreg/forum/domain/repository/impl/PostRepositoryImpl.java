/**
 * Created by Dawid Stankiewicz on 18.07.2016
 */
package com.github.szczypioreg.forum.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.szczypioreg.forum.domain.Post;
import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.PostRepository;

@Repository
@Transactional
public class PostRepositoryImpl implements PostRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Post getPostById(int id) {
        return entityManager.find(Post.class, id);
    }
    
    @Override
    public List<Post> getRecent(int count) {
        return (List<Post>) entityManager.createQuery("select p from Post p order by p.date desc")
                .setMaxResults(count).getResultList();
    }
    
    @Override
    public List<Post> getPostsByUser(User user) {
        return null;
    }
    
    @Override
    public void create(Post post) {
        entityManager.persist(post);
    }
    
}
