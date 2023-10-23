package com.example.github.domain.service;

import com.example.github.domain.repository.GithubRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class RepoDeleter {
    private final GithubRepository githubRepository;
    private final RepoRetreiver repoRetreiver;

    public void deleteById(Long id){
        repoRetreiver.existsById(id);
        log.info("Deleting repo with id: " + id);
        githubRepository.deleteById(id);
    }

}
