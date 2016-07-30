/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package com.github.szczypioreg.forum.service;

import java.util.Set;

import com.github.szczypioreg.forum.domain.Role;


public interface RoleService {
    
    Role findOne(int id);
    
    Role findByName(String name);
    
    Set<Role> findAll();
    
    void save(Role role);
    
    void save(String name);
    
    void delete(Role role);
    
    void delete(String name);
    
    void delete(int id);
    
}
