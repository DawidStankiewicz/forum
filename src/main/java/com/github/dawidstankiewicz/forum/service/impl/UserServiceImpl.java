/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.dawidstankiewicz.forum.service.impl;

import com.github.dawidstankiewicz.forum.entity.User;
import com.github.dawidstankiewicz.forum.entity.repository.UserRepository;
import com.github.dawidstankiewicz.forum.exception.UserNotFoundException;
import com.github.dawidstankiewicz.forum.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

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
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
