package com.eneam.gestionmission.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
	
	public void exportMissionsToExcel(List<Mission> missions, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Missions");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Conducteur");
        headerRow.createCell(1).setCellValue("Véhicule");
        headerRow.createCell(2).setCellValue("Destination");
        headerRow.createCell(3).setCellValue("Date de Début");
        headerRow.createCell(4).setCellValue("Date de Fin");

        int rowCount = 1;
        for (Mission mission : missions) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(mission.getConducteur().getNom() + " " + mission.getConducteur().getPrenom());
            row.createCell(1).setCellValue(mission.getVehicule().getMarque() + " " + mission.getVehicule().getModele());
            row.createCell(2).setCellValue(mission.getDestination());
            row.createCell(3).setCellValue(mission.getDateDebut().toString());
            row.createCell(4).setCellValue(mission.getDateFin().toString());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=missions.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
