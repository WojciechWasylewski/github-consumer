package com.example.github;

import com.example.github.client.proxy.GitHubBranchesResponse;
import com.example.github.client.proxy.GitHubResponse;
import com.example.github.client.proxy.GithubProxy;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class GithubConsumerApplication {
    @Autowired
    GithubProxy githubProxy;

    public static void main(String[] args) {
        SpringApplication.run(GithubConsumerApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        try {
            //Testowanie zapytań
            List<GitHubResponse> response = githubProxy.getAllUserRepos("kalqa", " application/json");
            System.out.println(response);
            System.out.println();

            List<GitHubBranchesResponse> response2 = githubProxy.getRepoInfo("kalqa", "03-open-feign");
            System.out.println(response2);

        } catch (FeignException.FeignClientException exception) {
            System.out.println("client exception " + exception.status());
            log.error("client exception: " + exception.status());
        } catch (FeignException.FeignServerException exception) {
            log.error("server exception " + exception.status());
        } catch (FeignException exception) {
            log.error(exception.getMessage() + " " + exception.status());
        }
    }

}
