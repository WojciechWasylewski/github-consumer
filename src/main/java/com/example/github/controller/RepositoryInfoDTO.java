package com.example.github.controller;

import java.util.Map;

public class RepositoryInfoDTO {
    private String repositoryName;
    private String ownerLogin;
    private Map<String, String> branchCommits;

    public RepositoryInfoDTO(String repositoryName, String ownerLogin, Map<String, String> branchCommits) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branchCommits = branchCommits;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public Map<String, String> getBranchCommits() {
        return branchCommits;
    }

    public void setBranchCommits(Map<String, String> branchCommits) {
        this.branchCommits = branchCommits;
    }
}

