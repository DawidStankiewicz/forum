/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.User;
import com.github.szczypioreg.forum.domain.repository.UserRepository;
import com.github.szczypioreg.forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @Override
    public void remove(int id) {
        remove(findOne(id));
    }
    
    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
    
}
