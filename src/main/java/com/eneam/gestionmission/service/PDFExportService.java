package com.eneam.gestionmission.service;


import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Paragraph;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Service
public class PDFExportService {
	
	public void exportMissionsToPDF(List<Mission> missions, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=missions.pdf");

        ServletOutputStream outputStream = response.getOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Table table = new Table(5);
        table.addCell(new Paragraph("Conducteur"));
        table.addCell(new Paragraph("Véhicule"));
        table.addCell(new Paragraph("Destination"));
        table.addCell(new Paragraph("Date de Début"));
        table.addCell(new Paragraph("Date de Fin"));

        for (Mission mission : missions) {
            table.addCell(new Paragraph(mission.getConducteur().getNom() + " " + mission.getConducteur().getPrenom()));
            table.addCell(new Paragraph(mission.getVehicule().getMarque() + " " + mission.getVehicule().getModele()));
            table.addCell(new Paragraph(mission.getDestination()));
            table.addCell(new Paragraph(mission.getDateDebut().toString()));
            table.addCell(new Paragraph(mission.getDateFin().toString()));
        }

        document.add(table);
        document.close();
        writer.close();
        outputStream.close();
    }

}
