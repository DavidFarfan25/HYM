package Reporte;

import DAO.ReporteDAO.ProductoComparativo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReporteExportador {

    public static void exportarPDF(List<ProductoComparativo> productos, String rutaArchivo) throws Exception {
        Document documento = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
        documento.open();

        com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph titulo = new Paragraph("Comparativa de Stock", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);

        String[] columnas = {"Código", "Nombre", "Stock Inicial", "Stock Final", "Variación"};
        for (String col : columnas) {
            PdfPCell celda = new PdfPCell(new Phrase(col));
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(celda);
        }

        for (ProductoComparativo p : productos) {
            tabla.addCell(p.getCodigo());
            tabla.addCell(p.getNombre());
            tabla.addCell(String.valueOf(p.getStockInicial()));
            tabla.addCell(String.valueOf(p.getStockFinal()));
            tabla.addCell(String.valueOf(p.getVariacion()));
        }

        documento.add(tabla);
        documento.close();
    }

    public static void exportarExcel(List<ProductoComparativo> productos, String rutaArchivo) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet hoja = workbook.createSheet("Comparativa");

        String[] columnas = {"Código", "Nombre", "Stock Inicial", "Stock Final", "Variación"};

        Row encabezado = hoja.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            encabezado.createCell(i).setCellValue(columnas[i]);
        }

        int fila = 1;
        for (ProductoComparativo p : productos) {
            Row row = hoja.createRow(fila++);
            row.createCell(0).setCellValue(p.getCodigo());
            row.createCell(1).setCellValue(p.getNombre());
            row.createCell(2).setCellValue(p.getStockInicial());
            row.createCell(3).setCellValue(p.getStockFinal());
            row.createCell(4).setCellValue(p.getVariacion());
        }

        for (int i = 0; i < columnas.length; i++) {
            hoja.autoSizeColumn(i);
        }

        FileOutputStream out = new FileOutputStream(rutaArchivo);
        workbook.write(out);
        out.close();
        workbook.close();
    }
}
