package Excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.usermodel.*;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;


import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openjfx.App;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ReadFile extends App {

    public String file(String path) {
        File file = new File(path);
        StringBuilder sb = new StringBuilder();

        int skipRow = 1;

        try(InputStream inputStream = new FileInputStream(path)) {
            DataFormatter formatter = new DataFormatter();

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                if(skipRow == 0) {
                    skipRow++;
                    continue;
                } else {
                    for (Cell cell : row) {
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    double numVal = cell.getNumericCellValue();
                                    int numericVal = (int) numVal;
                                    sb.append(numericVal).append(",");
                                    break;

                                case STRING:
                                    sb.append(cell.getStringCellValue()).append(",");
                                    break;
                                case FORMULA:
                                    sb.append(cell.getCellFormula()).append(",");

                            }
                        }
                    }
                    sb.append(";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String test = sb.toString();
        return sb.toString();
    }


    public String getCurWeekValues(String path) {
        File file = new File(path);
        StringBuilder sb = new StringBuilder();

        int skipRow = 0;

        try(InputStream inputStream = new FileInputStream(path)) {
            DataFormatter formatter = new DataFormatter();

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);


            for(Row row : sheet) {
                if(skipRow == 0) {
                    skipRow++;
                    continue;
                } else {
                    for (Cell cell : row) {
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    double numVal = cell.getNumericCellValue();
                                    int numericVal = (int) numVal;
                                    sb.append(numericVal).append(",");
                                    break;

                                case STRING:
                                    sb.append(cell.getStringCellValue()).append(",");
                                }

                        }
                    }
                    sb.append(";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public List<String> getValuesFromFile(String path, List<String> list) {
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        List<String> valueList = new ArrayList<>();
        List<String> restaurantList = new ArrayList<>();
        int skipRow = 1;


        try {
            DataFormatter formatter = new DataFormatter();


            FileInputStream fi = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fi);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                if(skipRow == 0) {
                    skipRow++;
                    continue;
                } else {
                    for (Cell cell : row) {
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    valueList.add(cell.getStringCellValue());
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        for(String restaurant : list) {
            for(String value : valueList) {
                if (restaurant.equals(value)) {
                    restaurantList.add(value);
                }
            }
        }
        List<String> sortedRestaurantList = restaurantList.stream().sorted().collect(Collectors.toList());

        return sortedRestaurantList;
    }
}
