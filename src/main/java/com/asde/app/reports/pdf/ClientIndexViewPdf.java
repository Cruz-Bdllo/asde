package com.asde.app.reports.pdf;

import com.asde.app.domain.Client;
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
 * Clase de servicio que muestra a través de la plantilla <b>/clients/homeClient</b> su formato en PDF, con el fin
 * de que el usuario pueda imprimir el contenido de la lista de clientes.
 */
@Service("/clients/homeClient")
public class ClientIndexViewPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        List<Client> clients = (List<Client>) model.get("clients");

        // Create Tables
        PdfPTable tableTitle = new PdfPTable(1);
        PdfPTable tableHeader = new PdfPTable(5);
        PdfPTable tableBody = new PdfPTable(5);


        // Config table Title
        PdfPCell cellTitle = new PdfPCell(new Phrase("Lista de clientes actuales"));
        cellTitle.setBorder(0);
        cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tableTitle.addCell(cellTitle);

        // Config table header
        PdfPCell cellHeader = new PdfPCell();
        Font fontHeader = new Font();
        fontHeader.setColor(Color.WHITE);
        fontHeader.setStyle(Font.BOLD);
        fontHeader.setSize(11);

        tableBody.setSpacingBefore(20);
        cellHeader.setBackgroundColor(new Color(39, 55, 70));
        cellHeader.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellHeader.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellHeader.setPaddingTop(15);
        cellHeader.setPaddingBottom(15);

        cellHeader.setPhrase(new Phrase("RFC", fontHeader));
        tableBody.addCell(cellHeader);

        cellHeader.setPhrase(new Phrase("Razón Social", fontHeader));
        tableBody.addCell(cellHeader);

        cellHeader.setPhrase(new Phrase("Correo", fontHeader));
        tableBody.addCell(cellHeader);

        cellHeader.setPhrase(new Phrase("Representante", fontHeader));
        tableBody.addCell(cellHeader);

        cellHeader.setPhrase(new Phrase("Teléfono", fontHeader));
        tableBody.addCell(cellHeader);

        // Fill table with clients
        PdfPCell cellData = new PdfPCell();
        cellData.setPaddingTop(8);
        cellData.setPaddingBottom(8);
        Font fontData = new Font();
        fontData.setSize(9);

        for(Client client : clients) {
            cellData.setPhrase(new Phrase(client.getRfc(), fontData));
            tableBody.addCell(cellData);

            cellData.setPhrase(new Phrase(client.getName(), fontData));
            tableBody.addCell(cellData);

            cellData.setPhrase(new Phrase(client.getEmail(), fontData));
            tableBody.addCell(cellData);

            if( client.getRepresentants().isEmpty() ){
                cellData.setPhrase(new Phrase("Sin representantes asignados", fontData));
            }else{
                cellData.setPhrase(new Phrase(client.getRepresentants().get(0).toString(), fontData));
            }
            tableBody.addCell(cellData);

            cellData.setPhrase(new Phrase(client.getPhone(), fontData));
            tableBody.addCell(cellData);
        }

        document.add(tableTitle);
        document.add(tableHeader);
        document.add(tableBody);
    }
} // end of class report
