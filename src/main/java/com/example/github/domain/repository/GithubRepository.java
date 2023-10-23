package com.example.github.domain.repository;

import com.example.github.domain.model.RepoInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<RepoInfo, Long> {
    @Query("SELECT r FROM RepoInfo r")
    List<RepoInfo> findAll();

    @Query("SELECT r FROM RepoInfo r WHERE r.id = :id")
    Optional<RepoInfo> findById(Long id);

    RepoInfo save(RepoInfo repoInfo);

    @Modifying
    @Query("DELETE RepoInfo r WHERE r.id = :id")
    void deleteById(Long id);

    boolean existsById(Long id);


}
