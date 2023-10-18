package com.example.github.controller;

import com.example.github.client.proxy.GitHubBranchesResponse;
import com.example.github.client.proxy.GitHubResponse;
import com.example.github.client.proxy.GithubProxy;
import com.example.github.controller.dto.response.GetAllUserRepoInfoResponseDto;
import com.example.github.controller.error.GithubUserNotFoundException;
import com.example.github.controller.error.InvalidFormatResponseError;
import feign.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/github.agent")
public class GithubRestController {

    private final GithubProxy githubProxy;

    public GithubRestController(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

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

    public boolean githubUserExists(String username) {
        Response response = githubProxy.checkUserExists(username);
        return response.status() == 200;
    }

}

