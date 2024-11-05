package com.security_vault.adapters.controller.exception;

public class TooManyRequest extends RuntimeException {
    public TooManyRequest() {
        super("Too many requests");
    }
}
