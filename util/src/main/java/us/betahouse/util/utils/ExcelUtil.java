package us.betahouse.util.utils;





import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.template.ExcelTemplate;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * @author zjb
 * @version : ExcelUtil.java 2019/8/1 14:50 zjb
 */
public class ExcelUtil {


    /**
     * 临时文件路径
     */
    private static final String TEMP_FILE_PATH = "./file/temp/";


    public static <T> HSSFWorkbook createExcel(List<T> list) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);
        for (int i = 0; i < list.size(); i++) {
            //利用反射机制获取所有成员字段
            Class<?> itemClass = list.get(i).getClass();
            Field[] fields = itemClass.getDeclaredFields();
            HSSFCell cell = null;
            HSSFRow row;

            //表头
            if (i == 0) {
                row = wb.getSheetAt(0).createRow((int) 0);
                for (int j = 0; j < fields.length; j++) {
                    boolean flag = fields[j].isAccessible();
                    fields[j].setAccessible(true);    //设置该成员字段可访问
                    cell = row.createCell((short) j);
                    cell.setCellValue(fields[j].getName());
                    fields[j].setAccessible(flag);
                }
            }

            //数据
            row = wb.getSheetAt(0).createRow((int) i + 1);
            for (int j = 0; j < fields.length; j++) {
                try {
                    boolean flag = fields[j].isAccessible();
                    fields[j].setAccessible(true);
                    cell = row.createCell((short) j);
                    cell.setCellValue(fields[j].get(list.get(i)) == null ? "" : fields[j].get(list.get(i)).toString());
                    fields[j].setAccessible(flag);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }


    /**
     * 将List对象转存为Excel临时文件
     *
     * @param list
     * @param filePath
     * @param <T>
     * @return
     */
    public static <T> String list2ExcelFile(List<T> list, String filePath, String fileName) {

        HSSFWorkbook wb = createExcel(list);
        String nowTime = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");


        String path = "";
        if (!fileName.endsWith(".xls")) fileName += ".xls";
        if (filePath == null || filePath == "") {
            path = TEMP_FILE_PATH + nowTime + fileName;
        } else {
            if (!filePath.endsWith("/")) filePath += "/";
            path = filePath + nowTime + fileName;
        }


        try {
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(path);
            } catch (FileNotFoundException e) {

                File newFile = new File(path);
                if (!newFile.getParentFile().exists()) {
                    try {
                        newFile.getParentFile().mkdirs();
                        fout = new FileOutputStream(path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;

    }

    public static List<ExcelTemplate> parseExcel(Workbook workbook) {
        List<ExcelTemplate> total=new ArrayList<>();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            ExcelTemplate excelTemplate=new ExcelTemplate();
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 校验sheet是否合法
            if (sheet == null||sheet.getRow(sheet.getFirstRowNum())==null) {
                continue;
            }
            //获取第一个实际行的行号
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            //实际测试lastRowNum总会多出一行？？？？？
            int lastRowNum=sheet.getLastRowNum();
            int firstColumn=sheet.getRow(firstRowNum+1).getFirstCellNum();
            int lastColumn=sheet.getRow(firstRowNum+1).getLastCellNum();
            int rowNum=lastRowNum-firstRowNum+1;
            String title;
            //有合并单元格，默认为标题,在第一行
            if(sheet.getNumMergedRegions()==1){
                CellRangeAddress mergedRegion = sheet.getMergedRegion(0);
                int fc=mergedRegion.getFirstColumn();
                title=convertCellValueToString(firstRow.getCell(fc));
                excelTemplate.setTitle(title);
                Map<String,List<String>> data=new HashMap<>();
                List<String> catalog=new ArrayList<>();
                //初始化data
                for (int i = firstColumn; i < lastColumn; i++) {
                    data.put(convertCellValueToString(sheet.getRow(firstRowNum+1).getCell(i)),new ArrayList<>());
                    catalog.add(convertCellValueToString(sheet.getRow(firstRowNum+1).getCell(i)));
                }
                for (int i = firstRowNum+2; i < lastRowNum+1; i++) {
                    if(sheet.getRow(i)==null){
                        rowNum--;
                        continue;
                    }
                    for (int j = firstColumn; j < lastColumn; j++) {
                        data.get(convertCellValueToString(sheet.getRow(firstRowNum+1).getCell(j))).add(convertCellValueToString(sheet.getRow(i).getCell(j)));
                    }
                }
                excelTemplate.setDate(data);
                excelTemplate.setColumnNum(lastColumn-firstColumn);
                excelTemplate.setRowNum(rowNum-2);
                excelTemplate.setCatalog(catalog);
                total.add(excelTemplate);
            }else if(sheet.getNumMergedRegions()==0){
                //没有标题的情况
                Map<String,List<String>> data=new HashMap<>();
                List<String> catalog=new ArrayList<>();
                //初始化data
                for (int i = firstColumn; i < lastColumn; i++) {
                    data.put(convertCellValueToString(sheet.getRow(firstRowNum).getCell(i)),new ArrayList<>());
                    catalog.add(convertCellValueToString(sheet.getRow(firstRowNum).getCell(i)));
                }
                for (int i = firstRowNum+1; i < lastRowNum+1; i++) {
                    if(sheet.getRow(i)==null){
                        rowNum--;
                        continue;
                    }
                    for (int j = firstColumn; j < lastColumn; j++) {
                        data.get(convertCellValueToString(sheet.getRow(firstRowNum).getCell(j))).add(convertCellValueToString(sheet.getRow(i).getCell(j)));
                    }
                }
                excelTemplate.setDate(data);
                excelTemplate.setColumnNum(lastColumn-firstColumn);
                excelTemplate.setRowNum(rowNum-1);
                excelTemplate.setCatalog(catalog);
                total.add(excelTemplate);
            }else {
                throw new BetahouseException(CommonResultCode.SYSTEM_ERROR,"表单中有多个合并行，无法解析");
            }
        }
        return total;
    }

        /**
         * 将单元格内容转换为字符串
         * @param cell
         * @return
         */
        private static String convertCellValueToString (Cell cell) {
            if (cell == null) {
                return null;
            }
            String returnValue = null;
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:   //数字
                    Double doubleValue = cell.getNumericCellValue();
                    // 格式化科学计数法，取一位整数
                    DecimalFormat df = new DecimalFormat("0");
                    returnValue = df.format(doubleValue);
                    break;
                case STRING:    //字符串
                    returnValue = cell.getStringCellValue();
                    break;
                case BOOLEAN:   //布尔
                    Boolean booleanValue = cell.getBooleanCellValue();
                    returnValue = booleanValue.toString();
                    break;
                case BLANK:     // 空值
                    break;
                case FORMULA:   // 公式
                    returnValue = cell.getCellFormula();
                    break;
                case ERROR:     // 故障
                    break;
                default:
                    break;
            }
            return returnValue;
        }

    }

