package com.example.github.infrastructure.controller;

import com.example.github.domain.model.RepoInfo;
import com.example.github.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.example.github.infrastructure.controller.dto.response.*;
import org.springframework.http.HttpStatus;

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

    public static DeleteRepoInfoDto mapFromRepoInfoToDeleteRepoInfoDto(Long id){
        return new DeleteRepoInfoDto("You deleted repository with id: " + id, HttpStatus.OK);
    }
}
