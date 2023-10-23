package com.example.github.infrastructure.controller;

import com.example.github.domain.model.Repo;
import com.example.github.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.example.github.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.example.github.infrastructure.controller.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RepoInfoMapper {
    private static RepoDto mapFromRepoInfoToRepoDto(Repo repo) {
        return new RepoDto(repo.getId(), repo.getOwner(), repo.getName());
    }

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto request) {
        return new Repo(request.owner(), request.name());
    }

    public static Repo mapFromUpdateRepoInfoRequestDtoToRepo(UpdateRepoRequestDto dto) {
        return new Repo(dto.name(), dto.owner());
    }

    public static CreateRepoResponseDto mapFromRepoInfoToCreateRepoResponseDto(Repo repo) {
        RepoDto repoDto = RepoInfoMapper.mapFromRepoInfoToRepoDto(repo);
        return new CreateRepoResponseDto(repoDto);
    }

    public static DeleteRepoInfoDto mapFromRepoInfoToDeleteRepoInfoDto(Long id) {
        return new DeleteRepoInfoDto("You deleted repository with id: " + id, HttpStatus.OK);
    }

    public static UpdateRepoResponseDto mapFromRepoInfoToUpdateRepoResponseDto(Repo newRepo) {
        return new UpdateRepoResponseDto(newRepo.getName(), newRepo.getOwner());
    }

    public static GetRepoResponseDto mapFromRepoInfoToGetRepoInfoResponseDto(Repo repo) {
        RepoDto repoDto = RepoInfoMapper.mapFromRepoInfoToRepoDto(repo);
        return new GetRepoResponseDto(repoDto);
    }

    public static GetAllReposResponseDto mapFromRepoInfoToGetAllReposResponseDto(List<Repo> repos) {
        List<RepoDto> repoDtos = repos.stream()
                .map(RepoInfoMapper::mapFromRepoInfoToRepoDto).toList();
        return new GetAllReposResponseDto(repoDtos);
    }
}
