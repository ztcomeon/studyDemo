package com.example.StudyDemo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

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

}