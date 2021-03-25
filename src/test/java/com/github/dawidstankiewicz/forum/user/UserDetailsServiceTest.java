package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import com.github.dawidstankiewicz.forum.model.entity.User;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserDetailsServiceTest
//        extends UnitsTestCase
{

//    @Autowired
//    private UserDetailsService service;

    @MockBean
    private UserService mockedUserService;

    private User mockedUser;

    @Before
    public void setup() {
//        createMockedUser();
//        setupMockedUserService();
    }
//
//    @Test
//    public void testLoadUserByUsername() {
//        assertNotNull(service.loadUserByUsername("test"));
//    }
//
//    @Test(expected = UsernameNotFoundException.class)
//    public void testLoadUserByUsernameAndExpectException() {
//        service.loadUserByUsername("undefined");
//    }
//
//    @Test
//    public void testLoadUserWithAuthorities() {
//        Role role = mockedUser.getRole();
//        UserDetails userDetails = service.loadUserByUsername("test");
//        String authorityName = userDetails.getAuthorities().iterator().next().getAuthority();
//
//        assertEquals(role.toString(), authorityName);
//    }
//
//    private void createMockedUser() {
//        mockedUser = new User();
//        mockedUser.setId(123);
//        mockedUser.setUsername("test");
//        mockedUser.setPassword("encrypted");
//
//        mockedUser.setRole(Role.USER);
//    }
//
//    private void setupMockedUserService() {
//        when(mockedUserService.findByUsername("test")).thenReturn(mockedUser);
//        when(mockedUserService.findByUsername("undefined")).thenThrow(new UserNotFoundException());
//    }
}
