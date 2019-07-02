package com.github.dawidstankiewicz.forum.exception;

import static com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode.*;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForumException extends RuntimeException {

    private ErrorCode errorCode = OK;

    private HttpStatus status = HttpStatus.OK;

    private String message;

    public ForumException() {
    }

    public ForumException(String message) {
        super(message);
    }

    public ForumException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = this.errorCode.message;
    }

    public enum ErrorCode {
        OK(1, "OK"),
        INVALID_GENDER(2, "Invalid user gender"),
        INVALID_USER_FORM(3, "Invalid user form"),
        CLIENT_ERROR(4, "Client side error"),
        INTERNAL_ERROR(5, "Internal server error"),
        SENDER_SERVICE_ERROR(6, "Email Sender Service error"),
        INVALID_ACTIVATION_REQUEST(7, "Invalid Activation request!");

        ErrorCode(int index, String message) {
            this.index = index;
            this.message = message;
        }

        public String message;
        public int index;
    }
}
