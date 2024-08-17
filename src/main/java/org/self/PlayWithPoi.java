package org.self;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlayWithPoi {

    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        // Create a new sheet
        HSSFSheet sheet = workbook.createSheet("Example Sheet");

        // Create a row and put some cells in it. Rows are 0-based.
        HSSFRow row = sheet.createRow(0);

        // Create cells and set their value
        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("Hello");

        HSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("World");

        HSSFCell cell3 = row.createCell(2);
        cell3.setCellValue(123.45);

        HSSFCell cell4 = row.createCell(3);
        cell4.setCellValue(12345);

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("workbook.xls");
             BufferedOutputStream os = new BufferedOutputStream(fileOut)) {

            workbook.write(os);
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
