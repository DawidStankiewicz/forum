package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.activation.ActivationSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationSenderService activationSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(User user) {
        userService.save(prepareUser(user));
        activationSenderService.sendActivationCode(user);
    }

    private User prepareUser(User user) {
        user.setPassword(getEncodedPassword(user.getPassword()));
        user.setActive(false);
        return user;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
