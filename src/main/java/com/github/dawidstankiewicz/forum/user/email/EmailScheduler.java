package com.github.dawidstankiewicz.forum.user.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailScheduler {

    private final EmailMessageRepository emailMessageRepository;

    public EmailScheduler(EmailMessageRepository emailMessageRepository) {
        this.emailMessageRepository = emailMessageRepository;
    }
}
