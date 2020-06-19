package com.asde.app.reports.pdf;

import com.asde.app.domain.Client;
import com.asde.app.domain.Representant;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Map;

@Service("/clients/details")
public class ClientDetailsPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Client client = (Client) model.get("client");

        // Create tables
        PdfPTable tableTitle = new PdfPTable(1);
        PdfPTable tableClient = new PdfPTable(2);
        PdfPTable tableRepresentant = new PdfPTable(4);

        // Config table title
        Font fontTitle = new Font();
        PdfPCell cellTitle = new PdfPCell();

        fontTitle.setSize(18);
        fontTitle.setSize(Font.BOLD);
        cellTitle.setBorder(0);
        cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellTitle.setPhrase(new Phrase("Detalles de "+client.getName()));
        tableTitle.addCell(cellTitle);
        tableTitle.setSpacingAfter(25);

        // Config table Client
        PdfPCell cellClientTitle = new PdfPCell();
        PdfPCell cellClientFiled = new PdfPCell();
        PdfPCell cellClientData = new PdfPCell();
        Font fontClient = new Font();

        fontClient.setColor(Color.WHITE);
        fontClient.setSize(14);
        fontClient.setStyle(Font.BOLD);


        cellClientTitle.setPaddingTop(8);
        cellClientTitle.setPaddingBottom(8);
        cellClientTitle.setBackgroundColor(Color.BLACK);
        cellClientTitle.setPhrase(new Phrase("Información general", fontClient));
        tableClient.addCell(cellClientTitle);
        tableClient.addCell(cellClientData);

        // General Details
        cellClientFiled.setBackgroundColor(Color.darkGray);

        Font fontClientField = new Font();
        fontClientField.setColor(Color.WHITE);
        fontClientField.setSize(11);

        Font fontClientData = new Font();
        fontClientData.setColor(Color.BLACK);
        fontClientData.setSize(11);

        cellClientFiled.setPhrase(new Phrase("Estado Actual", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getActive().toString()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("RFC", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getRfc()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("Razón Social", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getName()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("Correo corporativo", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getEmail()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("Teléfono", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getPhone()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("Celular", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getCellphone()));
        tableClient.addCell(cellClientData);

        cellClientFiled.setPhrase(new Phrase("Dirección de la empresa", fontClientField));
        tableClient.addCell(cellClientFiled);
        cellClientData.setPhrase(new Phrase(client.getAddress()));
        tableClient.addCell(cellClientData);


        // Config table to representants
        cellClientTitle.setColspan(4);
        cellClientTitle.setPhrase(new Phrase("Representantes", fontClient));
        tableRepresentant.setSpacingBefore(25);
        tableRepresentant.addCell(cellClientTitle);

        cellClientFiled.setPhrase(new Phrase("RFC", fontClientField));
        tableRepresentant.addCell(cellClientFiled);
        cellClientFiled.setPhrase(new Phrase("Nombre", fontClientField));
        tableRepresentant.addCell(cellClientFiled);
        cellClientFiled.setPhrase(new Phrase("Correo", fontClientField));
        tableRepresentant.addCell(cellClientFiled);
        cellClientFiled.setPhrase(new Phrase("Teléfono", fontClientField));
        tableRepresentant.addCell(cellClientFiled);

        for (Representant repre : client.getRepresentants()) {
            cellClientData.setPhrase(new Phrase(repre.getRfc(), fontClientData));
            tableRepresentant.addCell(cellClientData);
            cellClientData.setPhrase(new Phrase(repre.toString(), fontClientData));
            tableRepresentant.addCell(cellClientData);
            cellClientData.setPhrase(new Phrase(repre.getEmail(), fontClientData));
            tableRepresentant.addCell(cellClientData);
            cellClientData.setPhrase(new Phrase(repre.getPhone(), fontClientData));
            tableRepresentant.addCell(cellClientData);
        }


        document.add(tableTitle);
        document.add(tableClient);
        document.add(tableRepresentant);
    } // end method
} // end of class report
