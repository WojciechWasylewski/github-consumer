package com.example.github.domain.service;

import com.example.github.domain.model.RepoInfo;
import com.example.github.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
public class RepoAdder {
    private final GithubRepository githubRepository;

    public RepoAdder(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public RepoInfo addRepo(RepoInfo repoInfo) {
        log.info("adding new GitHub repository: " + repoInfo);
        return githubRepository.save(repoInfo);
    }
}
