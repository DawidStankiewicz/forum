/**
 * Created by Dawid Stankiewicz on 3 Jul 2016
 */
package com.github.szczypioreg.forum.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
}
