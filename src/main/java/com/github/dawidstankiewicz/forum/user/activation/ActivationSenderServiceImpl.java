package com.github.dawidstankiewicz.forum.user.activation;

import com.github.dawidstankiewicz.forum.user.User;
import com.github.dawidstankiewicz.forum.user.email.ActivationMessageGenerator;
import com.github.dawidstankiewicz.forum.user.email.EmailMessage;
import com.github.dawidstankiewicz.forum.user.email.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationSenderServiceImpl implements ActivationSenderService {

    @Autowired
    private ActivationCodeRepository repository;

    @Autowired
    private SenderService senderService;

    @Autowired
    private ActivationCodeGenerator activationCodeGenerator;

    @Autowired
    private ActivationMessageGenerator activationMessageGenerator;

    private ActivationCode activationCode;

    @Override
    public void sendActivationCode(User user) {
        createActivationCode(user);
        saveActivationCode();
        sendMessage();
    }

    private void createActivationCode(User user) {
        String generatedCode = activationCodeGenerator.generate();
        activationCode = new ActivationCode(generatedCode);
        activationCode.setUser(user);
    }

    private void saveActivationCode() {
        activationCode = repository.save(activationCode);
    }

    private void sendMessage() {
        EmailMessage emailMessage = activationMessageGenerator.generate(activationCode);
        senderService.sendEmail(emailMessage);
    }
}
