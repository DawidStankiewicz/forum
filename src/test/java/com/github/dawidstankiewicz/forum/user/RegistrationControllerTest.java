package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.Routes;
import com.github.dawidstankiewicz.forum.model.dto.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock private Model model;
    @Mock private BindingResult bindingResult;
    @Mock private UserRegistrationService registrationService;
    @Spy @InjectMocks private RegistrationController controller;


    @Test
    public void shoudlReturnRegistrationFormPage() {
        //when
        String result = controller.userRegistrationForm(model, null);
        //then
        assertEquals(Routes.NEW_USER_FORM, result);
        verify(model).addAttribute("userRegistrationForm", new UserRegistrationForm());
    }

    @Test
    public void shouldRegisterNewUser() {
        //given
        UserRegistrationForm form = UserRegistrationForm.builder().build();
        doReturn(false).when(bindingResult).hasErrors();
        User user = User.builder().username("user").build();
        doReturn(user).when(registrationService).registerUser(any());
        //when
        String result = controller.registerNewUser(form, bindingResult, model);
        //then
        assertEquals(Routes.REGISTRATION_CONFIRMATION, result);
        verify(registrationService).registerUser(form);
        verify(model).addAttribute("username", user.getUsername());
    }

    @Test
    public void shouldReturnValidationErrors() {
        //given
        UserRegistrationForm form = UserRegistrationForm.builder().build();
        doReturn(true).when(bindingResult).hasErrors();
        //when
        String result = controller.registerNewUser(form, bindingResult, model);
        //then
        assertEquals(Routes.NEW_USER_FORM, result);
        verify(registrationService, never()).registerUser(form);
    }

}
