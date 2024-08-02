package com.example.atiperatask.service;

import com.example.atiperatask.model.response.GetUserReposResponse;
import com.example.atiperatask.model.wrapper.BranchWrapper;
import com.example.atiperatask.model.wrapper.RepositoryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    public GetUserReposResponse getAllUserRepos(String apiUrl, String user) throws JsonProcessingException {
        String userReposUrl = apiUrl + "/users/" + user + "/repos";
        String reposResponse = restTemplate.getForObject(userReposUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode userReposRootNode = objectMapper.readTree(reposResponse);

        List<RepositoryWrapper> repoWrappers = new ArrayList<>();
        for (JsonNode userRepoNode : userReposRootNode) {
            boolean isFork = userRepoNode.path("fork").asBoolean();
            if(isFork) continue;

            String repoName = userRepoNode.get("name").asText();

            String repoBranchesUrl = apiUrl + "/repos/" + user + "/" + repoName + "/branches";
            String branchesResponse = restTemplate.getForObject(repoBranchesUrl, String.class);
            JsonNode repoBranchesRootNode = objectMapper.readTree(branchesResponse);

            List<BranchWrapper> branchWrappers = new ArrayList<>();
            for(JsonNode repoBranchNode : repoBranchesRootNode){
                String branchName = repoBranchNode.get("name").asText();
                JsonNode commitNode = repoBranchNode.get("commit");
                String lastCommitSha = commitNode.get("sha").asText();

                branchWrappers.add(BranchWrapper.builder()
                                .branchName(branchName)
                                .lastCommitSha(lastCommitSha)
                                .build());
            }

            repoWrappers.add(RepositoryWrapper.builder()
                            .repositoryName(repoName)
                            .ownerLogin(user)
                            .branches(branchWrappers)
                            .build());
        }

        return GetUserReposResponse.builder()
                .repos(repoWrappers)
                .build();
    }

    private static String extractJsonString(String response) {
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
