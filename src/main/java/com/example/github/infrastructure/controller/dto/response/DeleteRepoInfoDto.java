package com.example.github.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteRepoInfoDto(String message, HttpStatus status) {
}
