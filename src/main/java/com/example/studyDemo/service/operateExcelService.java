package com.example.studyDemo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-04-30
 * @since 1.0.0
 */
public interface operateExcelService {

    void exportExcel(HttpServletResponse response);

    void importExcel(MultipartFile file);
}