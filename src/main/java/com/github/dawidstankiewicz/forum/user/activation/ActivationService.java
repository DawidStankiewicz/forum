package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivationService {

    private final UserService userService;

    public ActivationService(UserService userService) {
        this.userService = userService;
    }

    public void activate(String username, String activationCodeId) {
        //todo
    }

}
