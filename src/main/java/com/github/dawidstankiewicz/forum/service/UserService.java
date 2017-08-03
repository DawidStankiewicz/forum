/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.dawidstankiewicz.forum.service;

import com.github.dawidstankiewicz.forum.entity.User;
import java.util.List;


public interface UserService {

    List<User> findAll();

    User findOne(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void create(User user);

    void remove(int id);

    void remove(User user);

    void remove(User user,
        String password);
}
