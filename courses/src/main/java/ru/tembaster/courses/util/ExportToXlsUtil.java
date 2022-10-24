package ru.tembaster.courses.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.tembaster.courses.to.ProfessorTo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportToXlsUtil {

    private Workbook workbook;
    private Sheet sheet;
    private List<ProfessorTo> professorList;

    public ExportToXlsUtil(List<ProfessorTo> list) {
        workbook = new XSSFWorkbook();
        professorList = list;
    }

    public void export(HttpServletResponse response) {
        writeHeader();
        writeData();
        try (ServletOutputStream sos = response.getOutputStream()) {
            workbook.write(sos);
            workbook.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    private void writeData() {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        for (int i = 0; i < professorList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(professorList.get(i).getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(professorList.get(i).getStudentsCount());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(professorList.get(i).getAvgPerformance());
            cell.setCellStyle(style);
        }
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Professors");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        Row header = sheet.createRow(0);

        Cell cell = header.createCell(0);
        cell.setCellValue("name");

        cell = header.createCell(1);
        cell.setCellValue("students count");

        cell = header.createCell(2);
        cell.setCellValue("average performance");
    }
}
