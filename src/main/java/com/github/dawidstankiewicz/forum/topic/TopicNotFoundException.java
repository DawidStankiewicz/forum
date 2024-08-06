package com.github.dawidstankiewicz.forum.topic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Topic not found")
public class TopicNotFoundException extends RuntimeException {
}
