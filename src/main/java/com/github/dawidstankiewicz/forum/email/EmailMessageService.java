package com.github.dawidstankiewicz.forum.email;

import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import com.github.dawidstankiewicz.forum.user.email.EmailMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailMessageService {

    @Autowired private EmailMessageRepository repository;

    public void scheduleMessage(EmailMessage message) {
        message.setScheduledSentDate(LocalDateTime.now());
        message.setSent(false);
        repository.save(message);
    }
}
