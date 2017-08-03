/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package com.github.dawidstankiewicz.forum.service;

import com.github.dawidstankiewicz.forum.entity.Role;
import java.util.List;


public interface RoleService {
    
    Role findOne(int id);
    
    Role findByName(String name);
    
    List<Role> findAll();
    
    void save(Role role);
    
    void save(String name,
              String description);
    
    void delete(Role role);
    
    void delete(String name);
    
    void delete(int id);
    
}
