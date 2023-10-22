package com.example.github.infrastructure.controller;

import com.example.github.domain.model.RepoInfo;
import com.example.github.infrastructure.controller.dto.response.GetAllReposResponseDto;
import com.example.github.infrastructure.controller.dto.response.GetRepoResponseDto;
import com.example.github.infrastructure.controller.dto.response.RepoDto;

import java.util.List;

public class RepoInfoMapper {
    public static GetAllReposResponseDto mapFromRepoInfoToGetAllReposResponseDto(List<RepoInfo> repos) {
        List<RepoDto> repoDtos = repos.stream()
                .map(RepoInfoMapper::mapFromRepoInfoToRepoDto).toList();
        return new GetAllReposResponseDto(repoDtos);
    }

    private static RepoDto mapFromRepoInfoToRepoDto(RepoInfo repoInfo) {
        return new RepoDto(repoInfo.getId(), repoInfo.getOwner(), repoInfo.getName());
    }

    public static GetRepoResponseDto mapFromRepoInfoToGetRepoInfoResponseDto(RepoInfo repoInfo){
        RepoDto repoDto = RepoInfoMapper.mapFromRepoInfoToRepoDto(repoInfo);
        return new GetRepoResponseDto(repoDto);
    }

    public static RepoInfo mapFromCreateRepoRequestDtoToRepoInfo(CreateRepoRequestDto request) {
        return new RepoInfo(request.ownerName(), request.repoName());
    }

    public static CreateRepoResponseDto mapFromRepoInfoToCreateRepoResponseDto(RepoInfo repoInfo){
        RepoDto repoDto = RepoInfoMapper.mapFromRepoInfoToRepoDto(repoInfo);
        return new CreateRepoResponseDto(repoDto);
    }
}
