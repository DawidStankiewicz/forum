package com.github.dawidstankiewicz.forum.user.email;

import com.github.dawidstankiewicz.forum.exception.ForumException;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SenderServiceImpl implements SenderService {

    private final Logger LOGGER = LoggerFactory.getLogger(SenderServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(EmailMessage emailMessage) throws ForumException {
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
        LOGGER.error("Mail Send Exception - smtp service unavailable");
        e.printStackTrace();
        throw new ForumException(ErrorCode.SENDER_SERVICE_ERROR);
    }
}
