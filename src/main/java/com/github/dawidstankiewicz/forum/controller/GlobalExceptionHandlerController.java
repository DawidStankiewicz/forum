/**
 * Created by Dawid Stankiewicz on 29.07.2016
 */
package com.github.dawidstankiewicz.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandlerController {
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "errors/404";
    }
    
}
