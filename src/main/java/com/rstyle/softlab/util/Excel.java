package com.rstyle.softlab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rstyle.softlab.projections.CustomProjection;

public class Excel {
	
	public static File createAndWriteToFile(List<CustomProjection> data) {
		File file = null;
		XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet sheet = workbook.createSheet("student Details"); 
        sheet.autoSizeColumn(1000000);
        
        int rowNum = 0;
        Row headers = sheet.createRow(rowNum);
        
        Cell hcell0 =  headers.createCell(0);
        hcell0.setCellValue("ФИО преподавателя");
        
        Cell hcell1 = headers.createCell(1);
        hcell1.setCellValue("Курс");
        		
        Cell hcell2 = headers.createCell(2);
        hcell2.setCellValue("Кол-во студентов");
        
        Cell hcell3 = headers.createCell(3);
        hcell3.setCellValue("Успеваемость");
        
        for(CustomProjection entry : data) {
        	rowNum++;
        	Row row = sheet.createRow(rowNum);
        	row.createCell(0).setCellValue(entry.getProfessorName());
        	row.createCell(1).setCellValue(entry.getCourseName());
        	row.createCell(2).setCellValue(entry.getStudentsAmout());
        	row.createCell(3).setCellValue(entry.getSuccessRate());
        }
        
        try { 
        	file = new File("successRate.xlsx");
            FileOutputStream out = new FileOutputStream(file); 
            workbook.write(out); 
            out.close(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }finally {
			try {
				if(workbook!=null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		
		return file;
	}
}
