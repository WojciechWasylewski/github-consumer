package com.example.github.controller.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorGithubResponseDto(HttpStatus status, String message) {
}
