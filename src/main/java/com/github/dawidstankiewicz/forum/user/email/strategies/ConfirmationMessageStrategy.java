package com.github.dawidstankiewicz.forum.user.email.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class ConfirmationMessageStrategy {

    @Autowired private TemplateEngine templateEngine;

    private final String TEMPLATE = "messages/activation_message";
    private final String ACTIVATION_CODE_VARIABLE = "activationCode";

    //todo
    public String createMessageContent(String activationCode) {
        Context context = new Context();
        context.setVariable(ACTIVATION_CODE_VARIABLE, activationCode);
        return templateEngine.process(TEMPLATE, context);
    }
}
