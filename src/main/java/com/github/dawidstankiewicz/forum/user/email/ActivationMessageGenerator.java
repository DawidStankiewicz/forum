package com.github.dawidstankiewicz.forum.user.email;

import com.github.dawidstankiewicz.forum.user.activation.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;

public abstract class ActivationMessageGenerator {

    @Autowired
    protected TemplateEngine templateEngine;

    private final String SUBJECT = "forum: activate your account!";

    public EmailMessage generate(ActivationCode activationCode) {
        String emailAddress = activationCode.getUser().getEmail();
        String content = createMessageContent(activationCode);

        EmailMessage emailMessage = new EmailMessage(emailAddress, SUBJECT, content);
        return emailMessage;
    }

    public abstract String createMessageContent(ActivationCode activationCode);

}
