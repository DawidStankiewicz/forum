package com.github.dawidstankiewicz.forum.security;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ForumUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public ForumUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Login user by email: " + email);
        User user = userService.findByEmailOrExit(email);
        Set<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                grantedAuthorities);
    }

    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user.getRoles() != null) {
            grantedAuthorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());
        }
        return grantedAuthorities;
    }
}
