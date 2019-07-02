package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.User;

public interface ActivationSenderService {

    void sendActivationCode(User user);

}
