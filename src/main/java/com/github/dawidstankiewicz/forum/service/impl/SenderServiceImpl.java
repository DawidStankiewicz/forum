package com.github.dawidstankiewicz.forum.service.impl;

import com.github.dawidstankiewicz.forum.model.EmailMessage;
import com.github.dawidstankiewicz.forum.service.SenderService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */

@Service
public class SenderServiceImpl implements SenderService {

    private final Logger LOGGER = LoggerFactory.getLogger(SenderServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(EmailMessage emailMessage) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = getMimeMessage(emailMessage, mail);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            // todo global exception handler
            LOGGER.error("Email to " + emailMessage.getRecipient() + " has not been sent!");
        }
        LOGGER.info("Email to " + emailMessage.getRecipient() + " has been sent!");
    }

    private MimeMessageHelper getMimeMessage(EmailMessage emailMessage,
        MimeMessage mail) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
        messageHelper.setTo(emailMessage.getRecipient());
        messageHelper.setSubject(emailMessage.getSubject());
        boolean HTMLFormat = true;
        messageHelper.setText(emailMessage.getContent(), HTMLFormat);
        return messageHelper;
    }
}
