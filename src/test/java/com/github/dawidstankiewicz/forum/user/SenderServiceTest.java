package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.github.dawidstankiewicz.forum.IntegrationTestCase;
import com.github.dawidstankiewicz.forum.exception.ForumException;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import com.github.dawidstankiewicz.forum.user.email.EmailMessage;
import com.github.dawidstankiewicz.forum.user.email.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderServiceTest extends IntegrationTestCase {

    @Autowired
    private SenderService senderService;

    @Test
    public void testSend() {
        senderService.sendEmail(getTestEmailMessage());

        assertTrue(testSmtp.waitForIncomingEmail(4000, 1));
    }

    @Test
    public void testStopSmtpServerShouldThrowException() {
        stopStmpServer();

        try {
            senderService.sendEmail(getTestEmailMessage());
            fail("Should Throw Exception cause smtp server is off");
        } catch (Exception e) {
            assertTrue(e instanceof ForumException);
            assertEquals(ErrorCode.SENDER_SERVICE_ERROR,
                ((ForumException) e).getErrorCode());
        }
    }

    @Test
    public void testSendInvalidMessageShouldThrowException() {
        try {
            senderService.sendEmail(getInvalidEmailMessage());
            fail("Should Throw Exception cause smtp server is off");
        } catch (Exception e) {
            assertTrue(e instanceof ForumException);
            assertEquals(ErrorCode.SENDER_SERVICE_ERROR,
                ((ForumException) e).getErrorCode());
        }
    }

    private EmailMessage getTestEmailMessage() {
        String to = "test@test.com";
        String subject = "test mail";
        String content = "test test test x2";
        return new EmailMessage(to, subject, content);
    }

    private EmailMessage getInvalidEmailMessage() {
        return new EmailMessage(null, null, null);
    }

    private void stopStmpServer() {
        this.testSmtp.stop();
    }
}