package com.io.v1.exportapp.service;

import com.io.v1.exportapp.dto.CryptoDataDTO;
import com.io.v1.exportapp.wrapper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class RESTService {
    private static final Logger log = LoggerFactory.getLogger(RESTService.class);
    private final WebClient webClient;

    public RESTService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<CryptoDataDTO> getWeeklyData() {
        String ENDPOINT = "/weekly";
        ApiResponse trendResponse = new ApiResponse();
        try {
            log.info("Calling API : " +ENDPOINT);
             trendResponse = webClient.get().uri(ENDPOINT)
                     .retrieve()
                    .bodyToMono(ApiResponse.class)
                    .block();
        } catch (Exception e){
            e.printStackTrace();
        }

        return trendResponse != null ? trendResponse.getTrend() : null;
    }
}
