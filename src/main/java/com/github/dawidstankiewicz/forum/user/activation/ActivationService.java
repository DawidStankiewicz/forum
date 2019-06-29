package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.User;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */
public interface ActivationService {

    void sendActivationCode(User user);

    void activate(String username, String id);
}
