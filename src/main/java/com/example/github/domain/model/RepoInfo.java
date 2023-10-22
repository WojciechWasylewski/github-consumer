package com.example.github.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "repo")
public class RepoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String owner;
    @Column(nullable = false)
    String name;

    public RepoInfo() {

    }
    public RepoInfo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public RepoInfo(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }


}
