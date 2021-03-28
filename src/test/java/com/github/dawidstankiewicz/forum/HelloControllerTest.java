package com.github.dawidstankiewicz.forum;

import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserRegistrationForm;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    @Mock private UserService userService;
    @Mock private RedirectAttributes model;
    @Spy @InjectMocks private HelloController controller;

    @Test
    public void shoudlReturnHelloPage() {
        assertEquals("hello", controller.hello());
    }

    @Test
    public void shoudlRedirectToLoginPage_WhenUserExists() {
        //given
        String email = "email";
        User user = User.builder().username("username").build();
        doReturn(user).when(userService).findByEmail(email);
        //when
        String result = controller.loginOrRegister(email, model);
        //then
        assertEquals("redirect:/login", result);
        verify(userService).findByEmail(email);
        verify(model).addFlashAttribute("email", email);
        verify(model).addFlashAttribute("username", user.getUsername());
    }

    @Test
    public void shoudlRedirectToNewUserForm_WhenNewUser() {
        //given
        String email = "email";
        User user = User.builder().build();
        doReturn(null).when(userService).findByEmail(email);
        //when
        String result = controller.loginOrRegister(email, model);
        //then
        assertEquals("redirect:/new-user", result);
        verify(model).addFlashAttribute("email", email);
    }
}
