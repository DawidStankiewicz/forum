package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.user.email.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(MockitoJUnitRunner.class)
public class SenderServiceTest {

    @Autowired
    private SenderService senderService;


    @Test
    public void shouldSendMessage() {
        //todo
    }

}