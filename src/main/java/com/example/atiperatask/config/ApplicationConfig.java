package com.example.atiperatask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Value("${api.token}")
    private String APIToken;

    public String getAPIToken() {
        return APIToken;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
