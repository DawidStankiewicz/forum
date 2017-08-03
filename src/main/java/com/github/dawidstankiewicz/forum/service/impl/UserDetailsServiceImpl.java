package com.github.dawidstankiewicz.forum.service.impl;

import com.github.dawidstankiewicz.forum.entity.Role;
import com.github.dawidstankiewicz.forum.entity.User;
import com.github.dawidstankiewicz.forum.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid Stankiewicz on 02.08.2017.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isActive(),
            true, true, true,
            grantedAuthorities);

        return userDetails;
    }
}
