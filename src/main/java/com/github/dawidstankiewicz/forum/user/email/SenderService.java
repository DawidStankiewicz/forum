package com.github.dawidstankiewicz.forum.user.email;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */
public interface SenderService {

    void sendEmail(EmailMessage emailMessage);

}
