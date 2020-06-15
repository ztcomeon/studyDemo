package com.example.studyDemo.controller;

import com.example.studyDemo.common.utils.ImageUtils;
import com.example.studyDemo.common.utils.XLSXUtils;
import com.example.studyDemo.service.operateExcelService;
import org.apache.commons.lang3.Validate;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-04-28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/operateExcel")
public class OperateExcelController {

  @Autowired operateExcelService operateExcelService;

  public final Logger LOG = LoggerFactory.getLogger(OperateExcelController.class);

  /**
   * 导出
   *
   * @param response
   */
  @RequestMapping(value = "/exportExcel")
  public void export(HttpServletResponse response) throws Exception {
    operateExcelService.exportExcel(response);
  }

  @RequestMapping(value = "/importExcel")
  public void imporExcel(@RequestParam("file") MultipartFile file) {
    operateExcelService.importExcel(file);
  }

  @PostMapping(value = "/fileUpload")
  public String fileUpload(
      @RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
    if (file.isEmpty()) {
      System.out.println("文件为空空");
    }
    String fileName = file.getOriginalFilename(); // 文件名
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
    String filePath = "D://temp-rainy//"; // 上传后的路径
    fileName = UUID.randomUUID() + suffixName; // 新文件名
    File dest = new File(filePath + fileName);
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdirs();
    }
    try {
      file.transferTo(dest);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String filename = "/temp-rainy/" + fileName;
    model.addAttribute("filename", filename);
    return "file";
  }

  @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
  public String uploadImg(@RequestParam(value = "file") MultipartFile image, String filePrefix) {
    try {
      if (image == null || image.isEmpty()) {
        throw new IllegalArgumentException("图片不能为空");
      }
      String contentType = image.getContentType();
      String oldFileName = image.getOriginalFilename();
      LOG.info("上传图片:name={},type={}", oldFileName, contentType);
      // 保存文件到相应位置
      Validate.notBlank(filePrefix, "存放的相对位置不能为空");
      String newFileName = ImageUtils.saveHeadImg(image, oldFileName, filePrefix);
      String relativePath = "/static/" + newFileName;
      return relativePath;
    } catch (Exception e) {
      return "error";
    }
  }
}
