package com.io.v1.exportapp.dao;

import com.io.v1.exportapp.dto.CryptoDataDTO;
import com.io.v1.exportapp.model.CryptoEntity;
import com.io.v1.exportapp.repo.CryptoRepo;
import com.io.v1.exportapp.service.ExcelService;
import com.io.v1.exportapp.service.RESTService;
import com.io.v1.exportapp.util.Builder;
import com.io.v1.exportapp.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CryptoDao {
    private final RESTService service;
    private final ExcelService excelExportService;
    private final CryptoRepo repo;
    private final Builder builder;
    private static final Logger log = LoggerFactory.getLogger(CryptoDao.class);
    public CryptoDao(RESTService service, ExcelService excelExportService, CryptoRepo repo, Builder builder) {
        this.service = service;
        this.excelExportService = excelExportService;
        this.repo = repo;
        this.builder = builder;
    }

    public void saveWeeklyData(){
        log.info("Inside saveWeeklyData() : ");
        ResponseVO vo = new ResponseVO();
        List<CryptoDataDTO> list = service.getWeeklyData();
        List<CryptoEntity> entities = list.stream()
                .map(dto -> builder.convertToEntity(dto))
                .collect(Collectors.toList());
        try {
            log.info("Saving entities");
            repo.saveAll(entities);
            vo.setMessage("Saved Successfully");
        } catch (Exception e){
            vo.setMessage("Error in saving records");
            e.printStackTrace();
        }
    }

    public byte [] exportWeeklyDataToExcel(){
        log.info("Inside exportWeeklyDataToExcel() : ");
        List<CryptoDataDTO> list = service.getWeeklyData();
        byte[] excelBytes = excelExportService.exportDataToExcel(list);
        return excelBytes;
    }
}
