package com.io.v1.exportapp.service;

import com.io.v1.exportapp.dto.CryptoDataDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExcelService {
    private static final Logger log = LoggerFactory.getLogger(ExcelService.class);
    public byte [] exportDataToExcel(List<CryptoDataDTO> list){
        log.info("Inside exportDataToExcel() : ");
        try (Workbook workbook = new XSSFWorkbook()) {
            LocalDate today = LocalDate.now();
            Sheet sheet = workbook.createSheet(today.toString());

            // Add sample data
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(1).setCellValue("ID");
            headerRow.createCell(2).setCellValue("BTC");
            headerRow.createCell(3).setCellValue("BTC_VOLUME");
            headerRow.createCell(4).setCellValue("Date");
            headerRow.createCell(5).setCellValue("LSE");
            headerRow.createCell(6).setCellValue("LSE_VOLUME");
            headerRow.createCell(7).setCellValue("NYSE");
            headerRow.createCell(8).setCellValue("NYSE_VOLUME");

            for(int i=1; i<list.size();i++) {
                CryptoDataDTO dto = list.get(i);
                Row dataRow = sheet.createRow(i);
                dataRow.createCell(1).setCellValue(dto.getId());
                dataRow.createCell(2).setCellValue(dto.getBtc());
                dataRow.createCell(3).setCellValue(dto.getBtcVol());
                dataRow.createCell(4).setCellValue(dto.getDate().toString());
                dataRow.createCell(5).setCellValue(dto.getLse());
                dataRow.createCell(6).setCellValue(dto.getLseVol());
                dataRow.createCell(7).setCellValue(dto.getNyse());
                dataRow.createCell(8).setCellValue(dto.getNyseVol());
            }

            // Write workbook to ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception or return an error response
            return new byte[0];
        }
    }
}
