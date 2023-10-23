package com.example.github.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateRepoRequestDto(
        @NotNull(message = "owner must be not null")
        @NotEmpty(message = "owner must be not empty")
        String owner,
        @NotNull(message = "name must be not null")
        @NotEmpty(message = "name must be not empty")
        String name

) {
}
