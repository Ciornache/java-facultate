package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataManipulation {

    private static ExcelDataManipulation instance = null;

    private ExcelDataManipulation() {

    }

    public static ExcelDataManipulation getInstance() {
        if(instance == null)
            instance = new ExcelDataManipulation();
        return instance;
    }

    public void extractData(String path) throws FileNotFoundException {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Worksheet");

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for(int row = firstRow + 1; row <= 100; ++row) {
                System.out.println(row);
                HikariDatabasePoolConnection.getConnection().close();

                Row currentRow = sheet.getRow(row);

                int releaseDateYear = -1;
                String albumName = null, artistName = null;
                List<String> genres = new ArrayList<>();

                for(int cell = currentRow.getFirstCellNum(); cell <= currentRow.getLastCellNum(); ++cell) {
                    Cell currentCell = currentRow.getCell(cell);
                    switch(cell)
                    {
                        case 1:
                            releaseDateYear = (int)currentCell.getNumericCellValue();
                            break;
                        case 2:
                            albumName = currentCell.getStringCellValue();
                            break;
                        case 3:
                            artistName = currentCell.getStringCellValue();
                            break;
                        case 4:
                            genres.add(currentCell.getStringCellValue());
                            break;
                        case 5:
                            genres.addAll(getSubGenres(currentCell.getStringCellValue()));
                            break;
                    }
                }
                Database.addDataToDatabase(releaseDateYear, albumName, artistName, genres);
            }


        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getSubGenres(String genres) {

        StringBuilder genre = new StringBuilder();
        List<String> subGenres = new ArrayList<>();
        for(int i = 0;i < genres.length(); ++i) {
            char c = genres.charAt(i);
            if(c == ',') {
                subGenres.add(genre.toString());
                genre = new StringBuilder();
                continue;
            }
            genre.append(genres.charAt(i));
        }
        return subGenres;
    }

    /// XSFF - versions of Excel 2007 and later
    /// HSFF - versions of EXCEL 2003 and earlier
    /// WHERE ARE [2004,2006] :(


}
