package com.example.atiperatask.controller;

import com.example.atiperatask.model.response.GetUserReposResponse;
import com.example.atiperatask.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/users/{user}/repos")
    public GetUserReposResponse getUserRepos(
            @PathVariable String user
    ) throws JsonProcessingException {
        String apiUrl = "https://api.github.com";
        return apiService.getAllUserRepos(apiUrl, user);
    }
}
