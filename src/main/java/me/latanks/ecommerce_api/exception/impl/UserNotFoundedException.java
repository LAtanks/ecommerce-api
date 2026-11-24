package me.latanks.ecommerce_api.exception.impl;

public class UserNotFoundedException extends RuntimeException {
    public UserNotFoundedException(String message) {
        super(message);
    }
}
