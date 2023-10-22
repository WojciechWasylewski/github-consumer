package com.example.github.infrastructure.controller.error;

public class GitHubRepoNotFoundException extends RuntimeException {
    public GitHubRepoNotFoundException(String message) {
        super(message);
    }
}
