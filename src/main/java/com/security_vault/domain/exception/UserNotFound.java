package com.security_vault.domain.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {super("User not found");}
}
