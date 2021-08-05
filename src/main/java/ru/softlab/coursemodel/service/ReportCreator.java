package ru.softlab.coursemodel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.softlab.coursemodel.model.dto.ProfessorReportDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportCreator {

    @Autowired
    private ProfessorService professorService;

//    TODO: Причесать код класса
    public void createXlsxReport() {
        List<ProfessorReportDto> professorReport = professorService.findProfessorReport();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Professors");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        CellStyle headerStyle = workbook.createCellStyle();
        initFont(workbook, headerStyle);
        initRow(sheet, headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        fillData(sheet, style, professorReport);

        writeFile(workbook);
    }

    private void initFont(Workbook workbook, CellStyle headerStyle) {
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
    }

    private void initRow(Sheet sheet, CellStyle headerStyle) {
        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("ФИО профессора");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Кол-во студентов по всем курсам");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Средняя успеваемость студентов");
        headerCell.setCellStyle(headerStyle);
    }

    private void fillData(Sheet sheet, CellStyle style, List<ProfessorReportDto> data) {
        for (int i = 0; i < data.size(); i++) {
            ProfessorReportDto dto = data.get(i);

            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(dto.getProfessorName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(dto.getStudentsAmount());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(dto.getCommonAveragePerformance());
            cell.setCellStyle(style);
        }
    }

    private void writeFile(Workbook workbook) {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "report.xlsx";

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
