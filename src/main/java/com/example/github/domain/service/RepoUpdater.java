package com.example.github.domain.service;

import com.example.github.domain.model.Repo;
import com.example.github.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class RepoUpdater {
    private final GithubRepository githubRepository;
    private final RepoRetreiver repoRetreiver;

    public void updateById(Long id, Repo newRepo){
        repoRetreiver.existsById(id);
        githubRepository.updateById(id, newRepo);
    }
}
