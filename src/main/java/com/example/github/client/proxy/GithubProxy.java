package com.example.github.client.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Component
@FeignClient(value = "server-github-client")
public interface GithubProxy {
    // GET https://api.github.com/users/{username}/repos
    @GetMapping("/users/{username}/repos")
    List<GitHubResponse> getAllUserRepos(
            @PathVariable String username,
            @RequestHeader("Accept") String acceptHeader
    );

    //GET  https://api.github.com/repos/OWNER/REPO/branches
    @GetMapping("/repos/{owner}/{repo}/branches")
    List<GitHubBranchesResponse> getRepoInfo(
            @PathVariable String owner,
            @PathVariable String repo);
}

