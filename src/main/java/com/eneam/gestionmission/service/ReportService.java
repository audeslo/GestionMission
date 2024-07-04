package com.eneam.gestionmission.service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.repository.MissionRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

	@Autowired
    private MissionRepository missionRepository;

    public ByteArrayInputStream exportConducteursEnMissionToExcel(LocalDate dateDebut, LocalDate dateFin) throws IOException {
        List<Mission> missions = missionRepository.findAllByDateDebutBetween(dateDebut, dateFin);

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Conducteurs en Mission");

            // Header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Conducteur");
            headerRow.createCell(1).setCellValue("Mission");
            headerRow.createCell(2).setCellValue("Date de début");
            headerRow.createCell(3).setCellValue("Date de fin");

            // Data
            int rowIdx = 1;
            for (Mission mission : missions) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(mission.getConducteur().getNom() + " " + mission.getConducteur().getPrenom());
                row.createCell(1).setCellValue(mission.getDestination());
                row.createCell(2).setCellValue(mission.getDateDebut().toString());
                row.createCell(3).setCellValue(mission.getDateFin().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportConducteursEnMissionToPDF(LocalDate dateDebut, LocalDate dateFin) {
        List<Mission> missions = missionRepository.findAllByDateDebutBetween(dateDebut, dateFin);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Conducteurs en Mission"));

            Table table = new Table(new float[]{3, 3, 3, 3});
            table.addHeaderCell("Conducteur");
            table.addHeaderCell("Mission");
            table.addHeaderCell("Date de début");
            table.addHeaderCell("Date de fin");

            for (Mission mission : missions) {
                table.addCell(mission.getConducteur().getNom() + " " + mission.getConducteur().getPrenom());
                table.addCell(mission.getDestination());
                table.addCell(mission.getDateDebut().toString());
                table.addCell(mission.getDateFin().toString());
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}