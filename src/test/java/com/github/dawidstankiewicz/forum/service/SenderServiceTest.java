package com.github.dawidstankiewicz.forum.service;

import static org.junit.Assert.assertTrue;

import com.github.dawidstankiewicz.forum.IntegrationTestConfig;
import com.github.dawidstankiewicz.forum.model.EmailMessage;
import javax.mail.MessagingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderServiceTest extends IntegrationTestConfig {

    @Autowired
    private SenderService senderService;

    @Test
    public void testSend() throws MessagingException {
        String to = "test@test.com";
        String subject = "test mail";
        String content = "test test test";
        senderService.sendEmail(new EmailMessage(to, subject, content));

        assertTrue(testSmtp.waitForIncomingEmail(4000, 1));
    }
}