package com.io.v1.exportapp.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.v1.exportapp.dto.CryptoDataDTO;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    @JsonProperty("trend")
    private List<CryptoDataDTO> trend;
}
