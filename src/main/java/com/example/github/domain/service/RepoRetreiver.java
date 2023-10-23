package com.example.github.domain.service;

import com.example.github.domain.model.RepoInfo;
import com.example.github.domain.repository.GithubRepository;
import com.example.github.infrastructure.controller.error.GitHubRepoNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepoRetreiver {
    private final GithubRepository githubRepository;

    public RepoRetreiver(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public List<RepoInfo> findAll(){
        return githubRepository.findAll();
    }

    public RepoInfo findById(Long id){
        return githubRepository.findById(id)
                .orElseThrow(() -> new GitHubRepoNotFoundException("Github repository with id: " + id + " not found"));
    }

    public void existsById(Long id) {
        if (!githubRepository.existsById(id)) {
            throw new GitHubRepoNotFoundException("Repo with id: " + id + " not found");
        }
    }

}
