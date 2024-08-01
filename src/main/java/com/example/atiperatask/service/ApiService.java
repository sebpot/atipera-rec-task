package com.example.atiperatask.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    public Object redirectRequest(String apiUrl) {
        return restTemplate.getForObject(apiUrl, Object.class);
    }
}
