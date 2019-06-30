package com.github.dawidstankiewicz.forum.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(GlobalExceptionHandlerController.class);


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "errors/404";
    }

    @ExceptionHandler(ForumApiException.class)
    public ResponseEntity<?> handleForumApiException(ForumApiException exception) {
        ForumApiExceptionResponse exceptionResponse = new ForumApiExceptionResponse(exception);
        LOGGER.error("Global Exception Handler {}", exceptionResponse);
        return new ResponseEntity(exceptionResponse, exception.getStatus());
    }

}
