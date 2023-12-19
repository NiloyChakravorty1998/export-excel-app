package com.io.v1.exportapp.controller;

import com.io.v1.exportapp.dto.CryptoDataDTO;
import com.io.v1.exportapp.model.CryptoEntity;
import com.io.v1.exportapp.repo.CryptoRepo;
import com.io.v1.exportapp.service.ExcelService;
import com.io.v1.exportapp.service.RESTService;
import com.io.v1.exportapp.util.Builder;
import com.io.v1.exportapp.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final RESTService service;
    private final ExcelService excelExportService;
    private final CryptoRepo repo;
    private final Builder builder;
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    public ApiController(RESTService service, CryptoRepo repo, Builder builder,ExcelService excelExportService) {
        this.service = service;
        this.excelExportService = excelExportService;
        this.repo = repo;
        this.builder = builder;
    }

    @PostMapping("/weekly")
    public ResponseEntity<?> saveWeeklyData(){
        log.info("Inside /weekly");
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
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportExcel() {
        log.info("Inside /export-excel");
        List<CryptoDataDTO> list = service.getWeeklyData();
        byte[] excelBytes = excelExportService.exportDataToExcel(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "exported_data.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }

}
