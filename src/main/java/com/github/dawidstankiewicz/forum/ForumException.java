package com.github.dawidstankiewicz.forum;

import static com.github.dawidstankiewicz.forum.ForumException.ErrorCode.*;

import lombok.Getter;

@Getter
public class ForumException extends RuntimeException {

    private ErrorCode errorCode = OK;

    public ForumException() {
    }

    public ForumException(String message) {
        super(message);
    }

    public ForumException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        OK,
        INVALID_GENDER
    }
}
