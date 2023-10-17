package com.example.github.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorGithubResponseDto(String message, HttpStatus status) {
}
