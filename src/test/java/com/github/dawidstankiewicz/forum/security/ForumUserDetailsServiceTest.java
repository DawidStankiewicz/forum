package com.github.dawidstankiewicz.forum.security;

import com.github.dawidstankiewicz.forum.model.entity.Role;
import com.github.dawidstankiewicz.forum.model.entity.User;
import com.github.dawidstankiewicz.forum.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ForumUserDetailsServiceTest {

    @Mock private UserService userService;
    @Spy @InjectMocks private ForumUserDetailsService userDetailsService;

    @Test
    public void shouldLoadUserByEmailUsername() {
        //given
        String email = "email@username.com";
        User user = User.builder().email(email).password("pass")
                .roles(new HashSet<>(
                        Arrays.asList(
                                Role.builder().name("USER").build(),
                                Role.builder().name("MOD").build()))).build();
        doReturn(user).when(userService).findByEmailOrExit(email);
        //when
        UserDetails result = userDetailsService.loadUserByUsername(email);
        //then
        assertNotNull(result);
        assertEquals(email, result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getRoles().size(), result.getAuthorities().size());
        verify(userService).findByEmailOrExit(email);
    }
}
