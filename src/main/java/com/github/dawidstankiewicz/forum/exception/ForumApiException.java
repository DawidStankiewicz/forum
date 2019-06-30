package com.github.dawidstankiewicz.forum.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ForumApiException extends ForumException {

    private HttpStatus status = HttpStatus.OK;

    private int statusCode = HttpStatus.OK.value();

    private String path;

    public ForumApiException(ErrorCode errorCode, HttpStatus status, String path) {
        super(errorCode);
        this.status = status;
        this.statusCode = status.value();
        this.path = path;
    }
}
