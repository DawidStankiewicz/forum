package com.github.dawidstankiewicz.forum.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.dawidstankiewicz.forum.exception.ForumApiExceptionResponse;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserCreationRestControllerExceptionsTest {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(UserCreationRestControllerExceptionsTest.class);

    private String API_ENDPOINT_URL = "/api/users";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CsrfTokenRepository csrfTokenRepository;

    @Test
    public void testShouldRespondInvalidUserFormError() throws Exception {
        UserCreationForm userCreationForm = new UserCreationForm();
        ResponseEntity<ForumApiExceptionResponse> result = makeRequest(userCreationForm);

        ErrorCode expectedErrorCode = ErrorCode.INVALID_USER_FORM;
        HttpStatus expectedStatusCode = HttpStatus.BAD_REQUEST;

        checkExpectedErrorAndStatusCode(result, expectedErrorCode, expectedStatusCode);
    }

    @Test
    public void testInvalidRequestShouldRespondCustomForumException() {
        ResponseEntity<ForumApiExceptionResponse> result = makeRequest(null);

//        LOGGER.info("testShouldRespondCustomForumException result: {}", result.toString());
        ErrorCode expectedErrorCode = ErrorCode.CLIENT_ERROR;
        HttpStatus expectedStatusCode = HttpStatus.BAD_REQUEST;

        checkExpectedErrorAndStatusCode(result, expectedErrorCode, expectedStatusCode);
    }

    @Test
    public void testShouldBe403IfWithoutCsrfToken() {
        ResponseEntity<ForumApiExceptionResponse> result = makeRequestWithoutCsrfToken();

        ErrorCode expectedErrorCode = ErrorCode.CLIENT_ERROR;
        HttpStatus expectedStatusCode = HttpStatus.FORBIDDEN;

        checkExpectedErrorAndStatusCode(result, expectedErrorCode, expectedStatusCode);
    }

    @Test
    public void testShouldBe415IfUnsupportedContentType() {
        ResponseEntity<ForumApiExceptionResponse> result = makeRequestWithUnsupportedContentType();

        ErrorCode expectedErrorCode = ErrorCode.CLIENT_ERROR;
        HttpStatus expectedStatusCode = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        checkExpectedErrorAndStatusCode(result, expectedErrorCode, expectedStatusCode);
    }

    private void checkExpectedErrorAndStatusCode(ResponseEntity<ForumApiExceptionResponse> result,
        ErrorCode expectedErrorCode, HttpStatus expectedStatusCode) {
        ForumApiExceptionResponse responseBody = result.getBody();

        assertEquals(expectedStatusCode.value(), result.getStatusCodeValue());
        assertEquals(expectedStatusCode.value(), responseBody.getStatusCode());
        assertEquals(expectedErrorCode, responseBody.getErrorCode());
        assertEquals(expectedStatusCode.toString(), responseBody.getStatus());
        assertEquals(API_ENDPOINT_URL, responseBody.getPath());
        assertNotNull(responseBody.getTimestamp());
        assertNotNull(responseBody.getMessage());
    }

    private ResponseEntity makeRequest(Object entity) {
        return this.testRestTemplate
            .postForEntity(API_ENDPOINT_URL,
                prepareRequest(entity),
                ForumApiExceptionResponse.class);
    }

    private HttpEntity prepareRequest(Object entity) {
        return new HttpEntity<>(entity, prepareHeaders());
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        CsrfToken csrfToken = csrfTokenRepository.generateToken(null);
        headers.add(csrfToken.getHeaderName(), csrfToken.getToken());
        headers.add("Cookie", "XSRF-TOKEN=" + csrfToken.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    private ResponseEntity<ForumApiExceptionResponse> makeRequestWithoutCsrfToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return this.testRestTemplate
            .postForEntity(API_ENDPOINT_URL,
                new HttpEntity<>(null, headers),
                ForumApiExceptionResponse.class);
    }

    private ResponseEntity<ForumApiExceptionResponse> makeRequestWithUnsupportedContentType() {
        HttpHeaders headers = prepareHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return this.testRestTemplate
            .postForEntity(API_ENDPOINT_URL,
                new HttpEntity<>(null, headers),
                ForumApiExceptionResponse.class);
    }
}
