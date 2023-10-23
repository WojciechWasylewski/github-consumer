package com.example.github.domain.repository;

import com.example.github.domain.model.Repo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GithubRepository extends Repository<Repo, Long> {
    @Query("SELECT r FROM Repo r")
    List<Repo> findAll(Pageable pageable);

    @Query("SELECT r FROM Repo r WHERE  r.id= :id")
    Optional<Repo> findById(Long id);

    Repo save(Repo repo);

    List<Repo> saveAll(Iterable<Repo> repos);

    @Modifying
    @Query("DELETE Repo r WHERE r.id = :id")
    void deleteById(Long id);

    boolean existsById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.name = :#{#newRepo.name}, r.owner = :#{#newRepo.owner} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);
}
