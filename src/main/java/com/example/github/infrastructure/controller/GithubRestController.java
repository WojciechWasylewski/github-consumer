package com.example.github.infrastructure.controller;

import com.example.github.client.proxy.GitHubBranchesResponse;
import com.example.github.client.proxy.GitHubResponse;
import com.example.github.client.proxy.GithubProxy;
import com.example.github.domain.model.Repo;
import com.example.github.domain.service.RepoAdder;
import com.example.github.domain.service.RepoDeleter;
import com.example.github.domain.service.RepoRetreiver;
import com.example.github.domain.service.RepoUpdater;
import com.example.github.infrastructure.controller.dto.request.CreateRepoRequestDto;
import com.example.github.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import com.example.github.infrastructure.controller.dto.response.*;
import com.example.github.infrastructure.controller.error.GithubUserNotFoundException;
import com.example.github.infrastructure.controller.error.InvalidFormatResponseError;
import feign.Response;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.github.infrastructure.controller.RepoInfoMapper.*;

@RestController
@Log4j2
@RequestMapping("/github.agent")
@AllArgsConstructor
public class GithubRestController {

    private final GithubProxy githubProxy;
    private final RepoRetreiver repoRetreiver;
    private final RepoAdder repoAdder;
    private final RepoDeleter repoDeleter;
    private final RepoUpdater repoUpdater;


    @GetMapping(value = "/{username}")
    public ResponseEntity<GetAllUserRepoInfoResponseDto> getAllRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if(acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)){
           throw new InvalidFormatResponseError("Requested media type 'application/xml' is not supported");
        }

        if (!githubUserExists(username)) {
            throw new GithubUserNotFoundException("User with username: " + username + " not found");
        }

        List<GitHubResponse> repositories = githubProxy.getAllUserRepos(username, "application/json");
        List<RepositoryInfoDTO> repositoryInfoList = new ArrayList<>();

        for (GitHubResponse repository : repositories) {
            List<GitHubBranchesResponse> branches = githubProxy.getRepoInfo(repository.owner().login(), repository.name());

            Map<String, String> branchInfo = new HashMap<>();

            for (GitHubBranchesResponse branch : branches) {
                branchInfo.put(branch.name(), branch.commit().sha());
            }
            RepositoryInfoDTO repositoryInfo = new RepositoryInfoDTO(repository.name(), repository.owner().login(), branchInfo);
            repositoryInfoList.add(repositoryInfo);
        }

        GetAllUserRepoInfoResponseDto responseDto = new GetAllUserRepoInfoResponseDto(repositoryInfoList);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/repos")
   public ResponseEntity<GetAllReposResponseDto> getAllRepos(@PageableDefault(page = 0, size = 10) Pageable pageable){
        List<Repo> allRepos = repoRetreiver.findAll(pageable);
        GetAllReposResponseDto response = mapFromRepoInfoToGetAllReposResponseDto(allRepos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/repos/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoById(@PathVariable Long id){
        Repo repo = repoRetreiver.findById(id);
        GetRepoResponseDto response = mapFromRepoInfoToGetRepoInfoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/repos")
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody @Valid CreateRepoRequestDto request) {
        Repo repo = RepoInfoMapper.mapFromCreateRepoRequestDtoToRepoInfo(request);
        Repo savedRepo = repoAdder.addRepo(repo);
        CreateRepoResponseDto body = mapFromRepoInfoToCreateRepoResponseDto(savedRepo);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/repos/{id}")
    public ResponseEntity<DeleteRepoInfoDto> deleteRepoById(@PathVariable Long id){
        repoDeleter.deleteById(id);
        DeleteRepoInfoDto body = mapFromRepoInfoToDeleteRepoInfoDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/repos/{id}")
    public ResponseEntity<UpdateRepoResponseDto> updateRepo(@PathVariable Long id, @RequestBody @Valid UpdateRepoRequestDto request){
        Repo newRepo = mapFromUpdateRepoInfoRequestDtoToRepo(request);
        repoUpdater.updateById(id,newRepo);
        UpdateRepoResponseDto body = mapFromRepoInfoToUpdateRepoResponseDto(newRepo);
        return ResponseEntity.ok(body);
    }

    public boolean githubUserExists(String username) {
        Response response = githubProxy.checkUserExists(username);
        return response.status() == 200;
    }

}

