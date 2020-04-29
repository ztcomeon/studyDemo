package com.example.studyDemo.controller;

import com.example.studyDemo.common.utils.XLSXUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/v1/exportExcel")
public class ExportExcelController {

    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletResponse response) throws Exception {
//     postman请求结果会有乱码（正常），浏l览器请求就不会有乱
//     设置文件名、表单名、标题栏
        String fileName = "员工信息表" + ".xlsx";
        String sheetname = "员工信息表";
        // 设置表头
        String[] title = {"编号", "姓名", "专业", "年龄", "入职日期"};
        //将需要导出的内容存进list集合中
        List<String> list = new ArrayList<>();
        list.add("hehe");
        list.add("tttt");
        // 声明表单内容
        String[][] content = new String[list.size()][title.length];
        // 遍历要导出的数据列表，构造表单内容
        for (int i = 0; i < list.size(); i++) {
            // 获取表单第i行
            String[] row = content[i];
            // 填充内容
            row[0] = list.get(0);
            row[1] = list.get(1);
        }

        // 获取文档
        XSSFWorkbook workbook = XLSXUtils.getWorkbook(sheetname, title, content);

        // 声明输出流
        OutputStream outputStream = null;
        // 响应到客户端
        try {
            // 设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            // 获取输出流
            outputStream = response.getOutputStream();

            // 用文档写输出流
            workbook.write(outputStream);

            // 刷新输出流
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭输出流
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}