package com.github.dawidstankiewicz.forum.exception;

import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import java.util.Date;
import lombok.Data;

@Data
public class ForumApiExceptionResponse {

    private int statusCode;
    private String status;
    private ErrorCode errorCode;
    private String message;
    private String path;
    private Date timestamp;

    public ForumApiExceptionResponse() {
        this.timestamp = new Date();
    }

    public ForumApiExceptionResponse(ForumApiException exception) {
        this();
        this.statusCode = exception.getStatus().value();
        this.status = exception.getStatus().toString();
        this.errorCode = exception.getErrorCode();
        this.message = exception.getMessage();
        this.path = exception.getPath();
    }
}
