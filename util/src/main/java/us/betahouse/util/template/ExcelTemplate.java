package us.betahouse.util.template;

import java.util.List;
import java.util.Map;

/**
 * Excel表格模板
 */
public class ExcelTemplate {
    /**
     * excel标题
     */
    String title;

    /**
     * excel数据
     */
    Map<String,List<String>> date;

    /**
     * 字段名目录
     */
    List<String> catalog;

    /**
     * Excel文件名
     */
    String fileName;

    /**
     * excel后缀名，有xls（2003）和xlsx（2007）
     */
    String ExcelType;

    /**
     * 总列数
     */
    int columnNum;

    /**
     * 总数据数(去除标题和字段名)
     */
    int rowNum;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, List<String>> getDate() {
        return date;
    }

    public void setDate(Map<String, List<String>> date) {
        this.date = date;
    }

    public List<String> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<String> catalog) {
        this.catalog = catalog;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExcelType() {
        return ExcelType;
    }

    public void setExcelType(String excelType) {
        ExcelType = excelType;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
}
