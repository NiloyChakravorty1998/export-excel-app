package com.io.v1.exportapp.util;

import com.io.v1.exportapp.dto.CryptoDataDTO;
import com.io.v1.exportapp.model.CryptoEntity;
import org.springframework.stereotype.Component;

@Component
public class Builder {

    public CryptoDataDTO convertToDto(CryptoEntity entity){
        CryptoDataDTO dto = new CryptoDataDTO();
        dto.setBtc(entity.getBtc());
        dto.setDate(entity.getDate());
        dto.setId(entity.getId());
        dto.setBtcVol(entity.getBtcVol());
        dto.setLse(entity.getLse());
        dto.setLseVol(entity.getLseVol());
        dto.setNyse(entity.getNyse());
        dto.setNyseVol(entity.getNyseVol());

        return dto;
    }

    public CryptoEntity convertToEntity(CryptoDataDTO dto){
        CryptoEntity entity = new CryptoEntity();
        entity.setBtc(dto.getBtc());
        entity.setDate(dto.getDate());
        entity.setId(dto.getId());
        entity.setBtcVol(dto.getBtcVol());
        entity.setLse(dto.getLse());
        entity.setLseVol(dto.getLseVol());
        entity.setNyse(dto.getNyse());
        entity.setNyseVol(dto.getNyseVol());

        return entity;
    }
}
