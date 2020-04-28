package com.example.StudyDemo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.text.DecimalFormat;

public class XLSXUtils {

  public static String getCellValue(Row row, int index) {
    String result = row.getCell(index) == null ? "" : getValue(row, index);
    if (StringUtils.isNotBlank(result)) {
      // 去除excel中的硬回车和软回车
      for (int i = 9; i < 14; i++) {
        result = result.replaceAll(String.valueOf((char) i), "");
      }
      result = result.replace(" ", "");
      result = result.replace("（", "(");
      result = result.replace("）", ")");
      result = result.replace(",", "");
    }
    return result;
  }

  private static String getValue(Row row, int index) {
    try {
      if (row.getCell(index) == null) {
        return "";
      }
      Cell cell = row.getCell(index);
      int type = cell.getCellType();
      String result = "";
      switch (type) {
        case Cell.CELL_TYPE_BLANK:
          break;
        case Cell.CELL_TYPE_BOOLEAN:
          break;
        case Cell.CELL_TYPE_ERROR:
          break;
        case Cell.CELL_TYPE_FORMULA:
          break;
        case Cell.CELL_TYPE_NUMERIC:
          if (DateUtil.isCellDateFormatted(cell)) {
            result = DateUtils.format(cell.getDateCellValue(), DateUtils.SHORT_DATE_FORMAT_STR);
          } else {
            double numericCellValue = cell.getNumericCellValue();
            if (numericCellValue % 1 == 0) {// 小数点后面是0，直接转为整数
              result =
                      String.valueOf(Integer.parseInt(new DecimalFormat("0").format(numericCellValue)));
            } else {// 小数点后面不是0，保留原数
              result = String.valueOf(numericCellValue);
            }
          }
          break;
        case Cell.CELL_TYPE_STRING:
          result = cell.getStringCellValue();
          break;
        default:
          break;
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  /**
   * 获取文档
   * @param sheetname 表单名
   * @param title 标题栏
   * @param content 内容
   * @return
   */
  public static XSSFWorkbook getWorkbook(String sheetname, String[] title, String[][] content) {
    // 新建文档实例
    XSSFWorkbook workbook = new XSSFWorkbook();

    // 在文档中添加表单
    XSSFSheet sheet = workbook.createSheet(sheetname);

    // 创建单元格格式，并设置居中
    XSSFCellStyle style = workbook.createCellStyle();
    style.setAlignment(HorizontalAlignment.CENTER);

    // 创建第一行，用于填充标题
    XSSFRow titleRow = sheet.createRow(0);
    // 填充标题
    for (int i = 0; i < title.length; i++) {
      // 创建单元格
      XSSFCell cell = titleRow.createCell(i);
      // 设置单元格内容
      cell.setCellValue(title[i]);
      // 设置单元格样式
      cell.setCellStyle(style);
    }

    // 填充内容
    for (int i = 0; i < content.length; i++) {
      // 创建行
      XSSFRow row = sheet.createRow(i + 1);
      // 遍历某一行
      for (int j = 0; j < content[i].length; j++) {
        // 创建单元格
        XSSFCell cell = row.createCell(j);
        // 设置单元格内容
        cell.setCellValue(content[i][j]);
        // 设置单元格样式
        cell.setCellStyle(style);
      }
    }

    // 返回文档实例
    return workbook;
  }
}