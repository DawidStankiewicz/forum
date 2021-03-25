package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;

        try {
            userDetails = tryToLoadUserByUsername(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(
                "User not found in UserDetailsService.loadByUsername");
        }

        return userDetails;
    }

    private UserDetails tryToLoadUserByUsername(String username) {
        User user = userService.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isActive(),
            true, true, true,
            grantedAuthorities);

        return userDetails;
    }
}
