package com.github.dawidstankiewicz.forum.user.activation;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.email.ActivationMessageGenerator;
import com.github.dawidstankiewicz.forum.user.email.EmailMessage;
import com.github.dawidstankiewicz.forum.user.email.SenderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ActivationSenderServiceTest extends UnitsTestCase {

    @Autowired
    private ActivationSenderService service;

    @MockBean
    private ActivationCodeRepository repository;

    @MockBean
    private SenderService sender;

    @MockBean
    private ActivationCodeGenerator activationCodeGenerator;

    @MockBean
    private ActivationMessageGenerator activationMessageGenerator;

    @Before
    public void setupSendActivationCode() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@forum.com");
        user.setPassword("password");

        ActivationCode activationCode = new ActivationCode("XXX");
        activationCode.setUser(user);

        EmailMessage emailMessage = new EmailMessage("x", "y", "z");

        when(repository.save(any(ActivationCode.class))).thenReturn(activationCode);
        when(activationMessageGenerator.generate(any(ActivationCode.class)))
            .thenReturn(emailMessage);

        service.sendActivationCode(user);
    }

    @Test
    public void testShouldCreateActivationCode() {
        verify(
            activationCodeGenerator,
            VerificationModeFactory.times(1))
            .generate();
    }

    @Test
    public void testShouldSaveActivationCode() {
        verify(
            repository,
            VerificationModeFactory.times(1))
            .save(any(ActivationCode.class));
    }

    @Test
    public void testShouldGenerateActivationCodeMessage() {
        verify(
            activationMessageGenerator,
            VerificationModeFactory.times(1))
            .generate(any(ActivationCode.class));
    }

    @Test
    public void testSendActivationCode() {
        verify(
            sender,
            VerificationModeFactory.times(1))
            .sendEmail(any(EmailMessage.class));
    }

}
