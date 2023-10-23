package com.example.github.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoRequestDto(
        @NotNull(message = "songName must be not null")
        @NotEmpty(message = "songName must be not empty")
        String name,
        @NotNull(message = "artist must be not null")
        @NotEmpty(message = "artist must be not empty")
        String owner
) {
}
