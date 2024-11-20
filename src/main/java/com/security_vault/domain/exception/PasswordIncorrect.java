package com.security_vault.domain.exception;

public class PasswordIncorrect extends RuntimeException {
    public PasswordIncorrect() {
        super("Password is incorrect");
    }
}
