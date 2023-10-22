package com.example.github.infrastructure.controller.dto.response;

import java.util.List;

public class GetAllUserRepoInfoResponseDto {
    private List<RepositoryInfoDTO> repositories;

    public GetAllUserRepoInfoResponseDto(List<RepositoryInfoDTO> repositories) {
        this.repositories = repositories;
    }

    public List<RepositoryInfoDTO> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryInfoDTO> repositories) {
        this.repositories = repositories;
    }
}

