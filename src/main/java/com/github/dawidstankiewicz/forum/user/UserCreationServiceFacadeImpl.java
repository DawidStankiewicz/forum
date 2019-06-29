package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.UserCreationServiceFacade;
import com.github.dawidstankiewicz.forum.user.activation.ActivationService;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid Stankiewicz on 04.08.2017.
 */

@Service
public class UserCreationServiceFacadeImpl implements UserCreationServiceFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        userService.save(user);

        activationService.sendActivationCode(user);
    }

}
