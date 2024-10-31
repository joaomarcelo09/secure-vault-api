package com.security_vault.domain.exception;

import java.util.function.Supplier;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {super("User not found");}
}
