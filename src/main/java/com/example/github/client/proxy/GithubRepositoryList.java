package com.example.github.client.proxy;

import java.util.List;

public class GithubRepositoryList {
    private List<GithubRepositoryInfo> repositories;

    public List<GithubRepositoryInfo> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GithubRepositoryInfo> repositories) {
        this.repositories = repositories;
    }
}
