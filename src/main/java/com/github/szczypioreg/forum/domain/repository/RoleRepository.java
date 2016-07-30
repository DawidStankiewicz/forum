/**
 * Created by Dawid Stankiewicz on 22.07.2016
 */
package com.github.szczypioreg.forum.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szczypioreg.forum.domain.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);
    
}
