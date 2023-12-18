package com.io.v1.exportapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FinanceData")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "BTC")
    private String btc;

    @Column(name = "NYSE")
    private String nyse;

    @Column(name = "LSE")
    private String lse;

    @Column(name = "BTC_Volume")
    private String btcVol;

    @Column(name="LSE_Volume")
    private String lseVol;

    @Column(name = "NYSE_Volume")
    private String nyseVol;
}
