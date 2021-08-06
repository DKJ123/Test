package Excel;

import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.draw.binding.CTPercentage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.*;

import java.awt.Color;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile {

    public void saveFile(TableView<NewFileValues> table, String path) {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Statistik");
        sheet.setColumnWidth(0, 9000);
        sheet.setColumnWidth(3, 2700);




        CellStyle percentStyle = wb.createCellStyle();
        percentStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));

        CellStyle headerStyle = wb.createCellStyle();



        Row row = sheet.createRow(0);
        row.setRowStyle(headerStyle);
        for(int i = 0; i < table.getColumns().size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(table.getColumns().get(i).getText());
        }

        int formulaNum1 = 2;
        int formulaNum2 = 2;

        for(int j = 0; j < table.getItems().size(); j++) {

            row = sheet.createRow(j + 1);
            for(int i = 0; i < table.getColumns().size(); i++) {
                if(table.getColumns().get(i).getCellData(i) != null) {
                    if(i == 0) {
                        row.createCell(i).setCellValue(table.getItems().get(j).getRestaurantNew());
                    }
                    if (i == 1) {
                        row.createCell(i).setCellValue(table.getItems().get(j).getPrevWeekNew());
                    }
                    if (i == 2) {
                        row.createCell(i).setCellValue(table.getItems().get(j).getCurWeekNew());
                    }
                    if(i == 3) {
                        row.createCell(i).setCellFormula("B" + formulaNum1 + "-C" + formulaNum1);
                        formulaNum1++;
                    }
                    if(i == 4) {
                        row.createCell(i).setCellFormula("IFERROR(D" + formulaNum2 + "/B" + formulaNum2 + ",C" + formulaNum2 + "*(-1))");
                        row.getCell(i).setCellStyle(percentStyle);
                        formulaNum2++;
                    }
                } else {
                    row.createCell(i).setCellValue("");
                }
            }
        }
        try {
            FileOutputStream fo = new FileOutputStream(path);
            wb.write(fo);
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        formatAsTable(path);
    }

    public void formatAsTable(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fi);
            XSSFSheet sheet = wb.getSheetAt(0);
            //sheet.setColumnWidth(0, 9000);
            //sheet.setColumnWidth(3, 2700);

            XSSFTable table = sheet.createTable();
            CTTable ctTable = table.getCTTable();

            CTTableStyleInfo tableStyleInfo = ctTable.addNewTableStyleInfo();
            tableStyleInfo.setName("TableStyleMedium9");
            tableStyleInfo.setShowColumnStripes(false);
            tableStyleInfo.setShowRowStripes(true);

            CellReference start = new CellReference(0, 0);
            CellReference end = new CellReference(sheet.getLastRowNum(), 4);
            AreaReference dataRangeStart = new AreaReference(start.formatAsString(), wb.getSpreadsheetVersion());
            AreaReference dataRangeEnd = new AreaReference(end.formatAsString(), wb.getSpreadsheetVersion());

            ctTable.setRef(dataRangeStart.formatAsString() + ":" + dataRangeEnd.formatAsString());
            ctTable.setDisplayName("MYTABLE");
            ctTable.setName("Name");
            ctTable.setId(1L);

            CTTableColumns columns = ctTable.addNewTableColumns();
            columns.setCount(5L);

            for (int i = 0; i < 5; i++) {
                CTTableColumn column = columns.addNewTableColumn();
                column.setName("Column" + i);
                column.setId(i + 1);
            }

            FileOutputStream fo = new FileOutputStream(path);
            wb.write(fo);
            fo.close();





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
