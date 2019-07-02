package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dawidstankiewicz.forum.IntegrationTestCase;
import com.github.dawidstankiewicz.forum.user.activation.ActivationCode;
import com.github.dawidstankiewicz.forum.user.activation.ActivationCodeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;

@Rollback
public class UserCreationRestControllerTest extends IntegrationTestCase {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    private MvcResult mvcResult;
    private String createdUserUsername = "test-1";
    private User createdUser;

    private String API_ENDPOINT_URL = "/api/users";

    @Before
    public void setup() throws Exception {
        createUser();
        createdUser = userService.findByUsername(createdUserUsername);
    }


    @Test
    public void testCreateStatus() {
        int expected201 = HttpStatus.CREATED.value();
        int actualStatus = mvcResult.getResponse().getStatus();

        assertEquals(expected201, actualStatus);
    }

    @Test
    public void testUserEntityShouldExist() {
        assertNotNull(createdUser);
        assertEquals(false, createdUser.isActive());
    }

    @Test
    public void testUserShouldHaveGender() {
        assertEquals(Gender.MALE, createdUser.getGender());
    }

    @Test
    public void testUserShouldHaveUndefinedRole() {
        assertEquals(Role.UNDEFINED, createdUser.getRole());
    }

    @Test
    public void testUserPasswordShouldBeEncodedAndFixedLength() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        int encodedPasswordLength = 60;
        String password = createdUser.getPassword();
        int actualPasswordValueLength = password.length();

        assertEquals(encodedPasswordLength, actualPasswordValueLength);
        assertTrue(passwordEncoder.matches("testtest", password));
        assertFalse(passwordEncoder.matches("test", password));
    }

    @Test
    public void testSaveActivationCode() {
        ActivationCode activationCode = activationCodeRepository.findAll().iterator().next();
        int expectedActivationCodeLength = 64;
        int actualActivationCodeLength = activationCode.getId().length();

        assertEquals(expectedActivationCodeLength, actualActivationCodeLength);
    }

    @Test
    public void testUserIdShouldBeAutoincrement() throws Exception {
        createTwoAnotherUsers();
        checkIfUsersHaveIdIncremented();
    }

    @Test
    public void testCreateAnotherUsers() throws Exception {
        createTwoAnotherUsers();
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void testShouldSendEmailWithActivationCode() {
        int timeout = 4000; // ms
        int expectedNumberOfEmails = 1;

        assertTrue(testSmtp.waitForIncomingEmail(timeout, expectedNumberOfEmails));
    }

    private void createUser() throws Exception {
        String parsedCreationForm = parseToJson(makeUserCreationForm(1));
        performRequestToCreateUser(parsedCreationForm);
    }

    private void createTwoAnotherUsers() throws Exception {
        List<UserCreationForm> userCreationForms = new ArrayList<>();
        userCreationForms.add(makeUserCreationForm(2));
        userCreationForms.add(makeUserCreationForm(3));

        for (UserCreationForm form : userCreationForms) {
            String parsedJson = parseToJson(form);
            performRequestToCreateUser(parsedJson);
        }
    }

    private void checkIfUsersHaveIdIncremented() {
        User test1 = userService.findByUsername("test-1");
        User test2 = userService.findByUsername("test-2");
        User test3 = userService.findByUsername("test-3");

        assertTrue(test2.getId() > test1.getId());
        assertTrue(test3.getId() > test2.getId());
    }

    private UserCreationForm makeUserCreationForm(int id) {
        String username = "test-" + id;
        UserCreationForm userCreationForm = new UserCreationForm();
        userCreationForm.setEmail(username + "@forum.com");
        userCreationForm.setUsername(username);
        userCreationForm.setPassword("testtest");
        userCreationForm.setName("Test");
        userCreationForm.setLastName("Test");
        userCreationForm.setGender(Gender.MALE);

        return userCreationForm;
    }

    private String parseToJson(Object object) {
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            System.err.println(e);
        }
        return json;
    }

    private void performRequestToCreateUser(String jsonBody) throws Exception {
        mvcResult =
            mockMvc
                .perform(post(API_ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody)).andReturn();
    }
}
