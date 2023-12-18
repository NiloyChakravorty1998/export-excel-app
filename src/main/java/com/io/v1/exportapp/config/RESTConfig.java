package com.io.v1.exportapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RESTConfig {
    private static final Logger log = LoggerFactory.getLogger(RESTConfig.class);
    @Value("${rest.base_uri}")
    private String BASE_URI;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        log.info("Initialized WebClient : ");
        return webClientBuilder.baseUrl(BASE_URI).build();
    }
}
