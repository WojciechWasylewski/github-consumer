package com.example.github.controller.error;

public class InvalidFormatResponseError extends RuntimeException {
    public InvalidFormatResponseError(String message) {
        super(message);
    }
}
