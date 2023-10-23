package com.example.github.domain.service;

import com.example.github.domain.model.Repo;
import com.example.github.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Transactional
public class RepoAdder {
    private final GithubRepository githubRepository;

    public RepoAdder(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public Repo addRepo(Repo repo) {
        log.info("adding new GitHub repository: " + repo);
        return githubRepository.save(repo);
    }

    public List<Repo> addAllRepos(List<Repo> repos){
        log.info("adding all repos from github api");
        return githubRepository.saveAll(repos);
    }
}
