package com.example.studyDemo.controller;

import com.example.studyDemo.common.utils.XLSXUtils;
import com.example.studyDemo.service.operateExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    operateExcelService operateExcelService;

    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response) throws Exception {
        operateExcelService.exportExcel(response);
    }


    @RequestMapping(value = "/importExcel")
    public void imporExcel(@RequestParam("file") MultipartFile file) {
        operateExcelService.importExcel(file);
    }
}