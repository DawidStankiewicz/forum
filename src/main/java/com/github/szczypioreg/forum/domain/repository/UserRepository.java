/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    List<User> findAll();
    
    User getUserByIdUser(int id);
    
    User getUserByUsername(String username);
    
    User getUserByEmail(String email);
    
}
