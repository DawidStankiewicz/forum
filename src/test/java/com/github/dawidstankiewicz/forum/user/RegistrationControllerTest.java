package com.github.dawidstankiewicz.forum.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock private Model model;
    @Spy @InjectMocks private RegistrationController controller;


    @Test
    public void shoudlReturnRegistrationFormPage() {
        //when
        String result = controller.userRegistrationForm(model, null);
        //then
        assertEquals("new_user_form", result);
        verify(model).addAttribute("userRegistrationForm", new UserRegistrationForm());
    }

}
