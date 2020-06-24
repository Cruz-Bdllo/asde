package com.asde.app.reports.pdf;

import com.asde.app.domain.Income;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Clase de servicio que muestra a través de la plantilla <b>/incomes/homeIncome</b> su formato en PDF, con el fin
 * de que el usuario pueda imprimir el contenido de la lista de todos los ingresos de trámites que ha realizado.
 */
@Service("/incomes/homeIncome")
public class IncomeIndexViewPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Income> incomes = (List<Income>) model.get("incomes");

        // Tables
        PdfPTable tableTitle = new PdfPTable(1);
        PdfPTable tableBody = new PdfPTable(5);

        // Title Document
        Font fontTitle = new Font();
        fontTitle.setStyle(Font.BOLD);
        fontTitle.setColor(Color.WHITE);
        fontTitle.setSize(16);

        PdfPCell cellTitle = new PdfPCell();
        cellTitle.setPaddingTop(10);
        cellTitle.setPaddingBottom(10);
        cellTitle.setBackgroundColor(new Color(52, 73, 94));
        cellTitle.setPhrase(new Phrase("Ingresos actuales", fontTitle));
        cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellTitle.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        tableTitle.addCell(cellTitle);

        // Header
        tableBody.setSpacingBefore(10);
        PdfPCell cellHeader = new PdfPCell();

        Font fontHeader = new Font();
        fontHeader.setSize(13);
        fontHeader.setColor(Color.WHITE);
        cellHeader.setBackgroundColor(new Color(36, 113, 163));
        cellHeader.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellHeader.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellHeader.setBorderColor(new Color(234, 237, 237));
        cellHeader.setPaddingTop(5);
        cellHeader.setPaddingBottom(5);

        cellHeader.setPhrase(new Phrase("Ingreso", fontHeader));
        tableBody.addCell(cellHeader);
        cellHeader.setPhrase(new Phrase("Estado", fontHeader));
        tableBody.addCell(cellHeader);
        cellHeader.setPhrase(new Phrase("Vigencia", fontHeader));
        tableBody.addCell(cellHeader);
        cellHeader.setPhrase(new Phrase("Empresa", fontHeader));
        tableBody.addCell(cellHeader);
        cellHeader.setPhrase(new Phrase("Trámite", fontHeader));
        tableBody.addCell(cellHeader);


        // Data
        PdfPCell cellData = new PdfPCell();
        Font fontData = new Font();

        fontData.setSize(10);
        fontData.setColor(new Color(44, 62, 80));

        cellData.setBorderColor(new Color(149, 165, 166));
        cellData.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellData.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellData.setBackgroundColor(Color.WHITE);

        if ( incomes.isEmpty() ) {
            cellData.setColspan(5);
            cellData.setPhrase(new Phrase("Sin ingresos", fontData));
            tableBody.addCell(cellData);
        } else{
          for ( Income income : incomes ) {
              cellData.setPhrase(new Phrase(income.getCreatAt().toString(), fontData));
              tableBody.addCell(cellData);

              cellData.setPhrase(new Phrase(income.getStatus().name(), fontData));
              tableBody.addCell(cellData);

              if ( income.getStatus().name().equals("PREVENCION") ) {
                  cellData.setPhrase(new Phrase("Prevención", fontData));
              }else if(income.getStatus().name().equals("COMPLETADO")) {
                  cellData.setPhrase(new Phrase((income.isExpirate()) ? "Vigente":"Renovar", fontData));
              }else{
                  cellData.setPhrase(new Phrase("Ingresado", fontData));
              }

              tableBody.addCell(cellData);

              cellData.setPhrase(new Phrase(income.getClient().getName(), fontData));
              tableBody.addCell(cellData);

              cellData.setPhrase(new Phrase(income.getProcedure().getName(), fontData));
              tableBody.addCell(cellData);
          }
        }

        document.add(tableTitle);
        document.add(tableBody);
    } // end report
} // end report
