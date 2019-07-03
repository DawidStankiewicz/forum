package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.user.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


public class UserServiceTest extends UnitsTestCase {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User mockedUser;
    private List<User> mockedUsers;

    @Before
    public void setup() {
        createMockedUser();
        createMockedUsers();
        setupMockedRepository();
    }

    @After
    public void reset() {
        resetMockedRepository();
    }

    @Test
    public void testFindAll() {
        List<User> resultUsers = userService.findAll();

        verify(userRepository, VerificationModeFactory.times(1)).findAll();
        assertEquals(1, resultUsers.size());
    }

    @Test
    public void testFindOne() {
        User resultUser = userService.findOne(123);

        verify(userRepository,
            VerificationModeFactory.times(1))
            .findById(123);
        assertEquals(mockedUser.getUsername(), resultUser.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindOneNotFoundException() {
        userService.findOne(999);
    }

    @Test
    public void testFindByUsername() {
        User resultUser = userService.findByUsername("test");

        verify(userRepository,
            VerificationModeFactory.times(1))
            .findByUsername("test");
        assertEquals(mockedUser.getId(), resultUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindByUsernameNotFoundException() {
        userService.findByUsername("undefined");
    }

    @Test
    public void testSave() {
        User createdUser = new User();
        createdUser.setUsername("createdUser");
        createdUser.setEmail("email@domain.com");
        createdUser.setPassword("encrypted");

        userService.save(createdUser);

        verify(userRepository,
            VerificationModeFactory.times(1))
            .save(createdUser);
    }

    private void createMockedUsers() {
        mockedUsers = new ArrayList<>();
        mockedUsers.add(mockedUser);
    }

    private void createMockedUser() {
        mockedUser = new User();
        mockedUser.setId(123);
        mockedUser.setUsername("test");
        mockedUser.setPassword("encrypted");
    }

    private void setupMockedRepository() {
        when(userRepository.findAll()).thenReturn(mockedUsers);
        when(userRepository.findById(123)).thenReturn(Optional.of(mockedUser));
        when(userRepository.findByUsername("test")).thenReturn(mockedUser);
    }

    private void resetMockedRepository() {
        Mockito.reset(userRepository);
    }
}
