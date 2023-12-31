package com.example.github.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "repo")
@NoArgsConstructor
@AllArgsConstructor
public class Repo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String owner;
    @Column(nullable = false)
    String name;
    public Repo(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }


}
