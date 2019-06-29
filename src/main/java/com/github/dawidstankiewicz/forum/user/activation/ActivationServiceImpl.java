package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.Role;
import com.github.dawidstankiewicz.forum.user.email.EmailMessage;
import com.github.dawidstankiewicz.forum.user.email.SenderService;
import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Dawid Stankiewicz on 10.08.2017.
 */

@Service
public class ActivationServiceImpl implements ActivationService {

    @Autowired
    private SenderService senderService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ActivationCodeService activationCodeService;

    @Autowired
    private UserService userService;

    @Override
    public void sendActivationCode(User user) {
        ActivationCode activationCode = new ActivationCode(user.getUsername());
        activationCodeService.save(activationCode);

        EmailMessage emailMessage = createEmailMessageWithActivationCode(activationCode,
            user.getEmail());

        senderService.sendEmail(emailMessage);
    }

    @Override
    public void activate(String username, String id) {
        User user = userService.findByUsername(username);
        ActivationCode activationCode = activationCodeService.findActivationCode(id);
        if (!activationCode.getUsername().equals(username)) {
            // todo throw new ApplicationException
        }
        user.setActive(true);
        user.setRole(Role.USER);
        userService.save(user);
    }

    private EmailMessage createEmailMessageWithActivationCode(ActivationCode activationCode,
        String email) {
        String subject = "forum: activation code";
        String content = createMessageContent(activationCode);

        return new EmailMessage(email, subject, content);
    }

    private String createMessageContent(ActivationCode activationCode) {
        String template = "messages/activation_message";

        Context context = new Context();
        context.setVariable("activationCode", activationCode);
        return templateEngine.process(template, context);
    }
}
