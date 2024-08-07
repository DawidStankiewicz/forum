package com.github.dawidstankiewicz.forum.user.email;

import com.github.dawidstankiewicz.forum.model.entity.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Slf4j
public class SenderService {

    private final JavaMailSender javaMailSender;

    public SenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(EmailMessage emailMessage) {
        try {
            tryParseAndSendEmail(emailMessage);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void tryParseAndSendEmail(EmailMessage emailMessage) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        parseMessage(emailMessage, mail);
        javaMailSender.send(mail);
    }

    private void parseMessage(EmailMessage emailMessage,
                              MimeMessage mail) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
        messageHelper.setTo(emailMessage.getRecipient());
        messageHelper.setSubject(emailMessage.getSubject());
        boolean HTMLFormat = true;
        messageHelper.setText(emailMessage.getContent(), HTMLFormat);
    }

    private void handleException(Exception e) {
        log.error("Mail Send Exception - smtp service unavailable");
        e.printStackTrace();
        throw new RuntimeException();
    }
}
