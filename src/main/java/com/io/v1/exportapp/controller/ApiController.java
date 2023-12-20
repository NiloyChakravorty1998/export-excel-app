package com.io.v1.exportapp.controller;

import com.io.v1.exportapp.dao.CryptoDao;
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

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final CryptoDao dao;
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    public ApiController(CryptoDao dao) {
        this.dao = dao;
    }

    @PostMapping("/weekly")
    public ResponseEntity<?> saveWeeklyData(){
        log.info("Inside /weekly");
        ResponseVO vo = new ResponseVO();
        try {
            log.info("Saving entities");
            dao.saveWeeklyData();
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
        byte [] excelBytes = dao.exportWeeklyDataToExcel();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "exported_data.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }
}
