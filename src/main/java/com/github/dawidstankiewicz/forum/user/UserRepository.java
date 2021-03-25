package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
}
