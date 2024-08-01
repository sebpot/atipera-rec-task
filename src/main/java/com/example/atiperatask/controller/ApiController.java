package com.example.atiperatask.controller;

import com.example.atiperatask.service.ApiService;
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

    @GetMapping("/users/{user}")
    public Object GetUserRepos(
            @PathVariable String user
    ){
        String redirectUrl = "https://api.github.com/users/" + user + "/repos";
        return apiService.redirectRequest(redirectUrl);
    }
}
