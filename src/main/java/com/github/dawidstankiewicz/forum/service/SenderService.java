package com.github.dawidstankiewicz.forum.service;

import com.github.dawidstankiewicz.forum.model.EmailMessage;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */
public interface SenderService {

    void sendEmail(EmailMessage emailMessage);

}
