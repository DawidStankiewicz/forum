/**
 * Created by Dawid Stankiewicz on 11.07.2016
 */
package com.github.dawidstankiewicz.forum.user;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User findOne(int id);

    User findByUsername(String username);

    User save(User user);

}
