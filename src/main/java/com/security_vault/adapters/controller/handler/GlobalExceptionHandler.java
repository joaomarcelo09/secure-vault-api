package com.security_vault.adapters.controller.handler;

import com.security_vault.adapters.controller.exception.TooManyRequest;
import com.security_vault.domain.exception.PasswordIncorrect;
import com.security_vault.domain.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(PasswordIncorrect.class)
    public ResponseEntity<String> handlePasswordIncorrec(PasswordIncorrect exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(TooManyRequest.class)
    public ResponseEntity<String> handleTooManyRequest(TooManyRequest exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(exception.getMessage());
    }
}
