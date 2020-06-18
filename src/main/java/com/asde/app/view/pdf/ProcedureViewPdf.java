package com.asde.app.view.pdf;

import com.asde.app.domain.Procedure;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("/procedures/homeProcedure")
public class ProcedureViewPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        List<Procedure> procedures = (List<Procedure>) model.get("procedures");

        PdfPCell cellTi = new PdfPCell();
        cellTi.setPhrase(new Phrase("Lista de trámites"));
        cellTi.setBorder(0);
        cellTi.setHorizontalAlignment(PdfCell.ALIGN_CENTER);



        Font font = new Font();
        font.setStyle(Font.BOLD);
        font.setColor(Color.WHITE);
        font.setSize(14);


        PdfPTable tableTitle = new PdfPTable(1);
        PdfPTable table = new PdfPTable(4);
        table.setSpacingBefore(20);

        tableTitle.addCell(cellTi);

        PdfPCell cellTitle = new PdfPCell();
        cellTitle.setBackgroundColor(new Color(39, 55, 70));
        cellTitle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellTitle.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellTitle.setPaddingBottom(15);
        cellTitle.setPaddingTop(15);

        cellTitle.setPhrase(new Phrase("NOMBRE", font));
        table.addCell(cellTitle);

        cellTitle.setPhrase(new Phrase("VIGENCIA", font));
        table.addCell(cellTitle);

        cellTitle.setPhrase(new Phrase("TIPO DE TRAMITE", font));
        table.addCell(cellTitle);

        cellTitle.setPhrase(new Phrase("LUGAR DE INGRESO", font));
        table.addCell(cellTitle);

        PdfPCell cellElement = new PdfPCell();
        cellElement.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellElement.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellElement.setPaddingTop(10);
        cellElement.setPaddingBottom(10);

        Font fontElement = new Font();
        fontElement.setSize(10);

        for (Procedure procedure : procedures) {
            cellElement.setPhrase(new Phrase(procedure.getName(), fontElement));
            table.addCell(cellElement);
            cellElement.setPhrase(new Phrase(procedure.getExpiration(), fontElement));
            table.addCell(cellElement);
            cellElement.setPhrase(new Phrase(procedure.getType().name(), fontElement));
            table.addCell(cellElement);
            cellElement.setPhrase(new Phrase(procedure.getDependence().getName(), fontElement));
            table.addCell(cellElement);
        }

        HeaderFooter header = new HeaderFooter(new Phrase("This is a header."), false);
        document.setFooter(header);
        document.add(tableTitle);
        document.add(table);

    }
}
