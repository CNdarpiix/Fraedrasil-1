package com.fraedrasil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerUserNotFound(UserNotFoundException e) {
        return e.getMessage();
    }
}
