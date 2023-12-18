package com.io.v1.exportapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDataDTO {
    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("BTC")
    private String btc;

    @JsonProperty("NYSE")
    private String nyse;

    @JsonProperty("LSE")
    private String lse;

    @JsonProperty("BTC_Volume")
    private String btcVol;

    @JsonProperty("LSE_Volume")
    private String lseVol;

    @JsonProperty("NYSE_Volume")
    private String nyseVol;
}
