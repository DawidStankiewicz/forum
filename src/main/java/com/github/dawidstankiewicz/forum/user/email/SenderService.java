package com.github.dawidstankiewicz.forum.user.email;

import com.github.dawidstankiewicz.forum.exception.ForumException;

public interface SenderService {

    void sendEmail(EmailMessage emailMessage) throws ForumException;

}
