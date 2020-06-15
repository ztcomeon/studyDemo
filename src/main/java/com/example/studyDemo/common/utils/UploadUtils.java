package com.example.studyDemo.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author wangyu @Date 2018-08-03 10:20
 */
@Component
@SuppressWarnings("static-access")
public class UploadUtils {

  private static String win_location;

  private static String linux_location;

  /** 获取文件后缀 */
  public static String getFileSuffix(String fileName) {
    String suffix = fileName.substring(fileName.lastIndexOf("."));
    return suffix;
  }

  /** 重新命名图片,通用唯一识别码作为文件名，保存上传的图片不重复 */
  public static String reNameFile(String fileName) {
    String newFileName = UUID.randomUUID() + getFileSuffix(fileName);
    return newFileName;
  }

  /** 判断当前系统，获取保存图片的路径 */
  public static String getPath() {
    String property = System.getProperty("os.name");
    if (property.startsWith("Win")) {
      return win_location;
    } else {
      return linux_location;
    }
  }
  // 使用set方法将值注入
  @Value("${img.win_location}")
  public void setWin_location(String win_location) {
    this.win_location = win_location;
  }

  @Value("${img.linux_location}")
  public void setLinux_location(String linux_location) {
    this.linux_location = linux_location;
  }
}
