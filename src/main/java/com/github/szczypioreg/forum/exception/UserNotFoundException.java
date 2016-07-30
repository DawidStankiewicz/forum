/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.szczypioreg.forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User does not exist!")
public class UserNotFoundException extends RuntimeException {}
