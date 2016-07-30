/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package com.github.szczypioreg.forum.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szczypioreg.forum.domain.Role;
import com.github.szczypioreg.forum.domain.repository.RoleRepository;
import com.github.szczypioreg.forum.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public Role findOne(int id) {
        return roleRepository.findOne(id);
    }
    
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    
    @Override
    public Set<Role> findAll() {
        return (Set<Role>) roleRepository.findAll();
    }
    
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
    
    @Override
    public void save(String name) {
        save(new Role(name));
    }
    
    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }
    
    @Override
    public void delete(String name) {
        delete(findByName(name));
    }
    
    @Override
    public void delete(int id) {
        delete(findOne(id));
    }
    
}
