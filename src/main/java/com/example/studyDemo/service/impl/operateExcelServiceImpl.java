package com.example.studyDemo.service.impl;

import com.example.studyDemo.common.utils.XLSXUtils;
import com.example.studyDemo.entity.TestEntity;
import com.example.studyDemo.entity.TestEntity02;
import com.example.studyDemo.repository.Test02Repository;
import com.example.studyDemo.repository.TestRepository;
import com.example.studyDemo.service.operateExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-04-30
 * @since 1.0.0
 */
@Service("operateExcelService")
public class operateExcelServiceImpl implements operateExcelService {


    private static final Logger LOG = LoggerFactory.getLogger(operateExcelServiceImpl.class);


    @Autowired
    TestRepository testRepository;

    @Autowired
    Test02Repository test02Repository;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Override
    public void exportExcel(HttpServletResponse response) {

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

    @Override
    public void importExcel(MultipartFile file) {
        // 1.先获取导入文件的后缀名 判断是xls 还是xlsx
        String prefix = null;
        String originalFilename = file.getOriginalFilename();
        //获取点的位置
        int prefixIndex = originalFilename.lastIndexOf(".");
        if (prefixIndex != -1) {
            //获取后缀名
            prefix = originalFilename.substring(prefixIndex + 1);
            prefix = prefix.toUpperCase();
        }

        // 2.将上传的文件根据不同的文件格式 读入对应的excel处理器中
        HSSFWorkbook hwb = null;
        XSSFWorkbook xwb = null;
        // 总的工作薄数量
        // int totalSheet = 0;
        // 当前工作簿
        Sheet currentSheet = null;
        int sheetIndex = 0;
        if ("XLSX".equals(prefix)) {
            try {
                xwb = new XSSFWorkbook(file.getInputStream());
                // totalSheet = xwb.getNumberOfSheets();
            } catch (IOException e) {
                LOG.error(originalFilename, e);
            }
        } else if ("XLS".equals(prefix)) {
            try {
                hwb = new HSSFWorkbook(file.getInputStream());
                // totalSheet = hwb.getNumberOfSheets();
            } catch (IOException e) {
                LOG.error(originalFilename, e);
            }
        } else {
            throw new IllegalArgumentException("文件格式错误!!");
        }

        XSSFSheet sheet = xwb.getSheetAt(0);
        //多线程导入
        int len = sheet.getLastRowNum();//读出总行数
        if (len > 0) {
            // int threadNum = Runtime.getRuntime().availableProcessors() * 2; // 设置可用线程数
            int threadNum = threadPoolTaskExecutor.getMaxPoolSize();
            int dataSize = len; // 待处理总数
            int threadSize = (dataSize / threadNum) == 0 ? 1 : dataSize / threadNum; // 线程分段处理数(保证线程执行的数量最低为1，避免除数为0)
            boolean special = dataSize % threadSize == 0; // 判断余数是否为0，即是否整除
            // List<TestEntity> cutList = null;
            List<TestEntity02> cutList = null;
            for (int i = 0; i < threadNum; i++) {
                if (special) {//整除时
                    if ((i + 1) * threadSize > dataSize) {
                        break;
                    } else {
                        // cutList = getDataList(sheet, threadSize, threadSize * (i + 1));
                        cutList = getDataList02(sheet, threadSize, threadSize * (i + 1));
                    }

                } else {//非整除时
                    if ((i + 1) == threadNum) {
                        // cutList = getDataList(sheet, threadSize * i, dataSize);
                        cutList = getDataList02(sheet, threadSize * i, dataSize);
                    } else {
                        // cutList = getDataList(sheet, threadSize * i, threadSize * (i + 1));
                        cutList = getDataList02(sheet, threadSize * i, threadSize * (i + 1));
                    }
                }
                // List<TestEntity> finalCutList = cutList;
                List<TestEntity02> finalCutList = cutList;
                threadPoolTaskExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    // testRepository.saveAll(finalCutList);
                    test02Repository.saveAll(finalCutList);
                });
            }
        }

    }

    private List<TestEntity> getDataList(XSSFSheet sheet, int start, int end) {
        List<TestEntity> cutList = new ArrayList<>();
        for (int j = start; j < end; j++) {
            String a = XLSXUtils.getCellValue(sheet.getRow(j), 0);
            //导入的时候进行查询，防止重复，但是效率比较低
            List<TestEntity> result = testRepository.findByName(a);
            if (result == null || result.size() == 0) {
                TestEntity testEntity = new TestEntity();
            testEntity.setName(a);
            cutList.add(testEntity);
            }


        }
        return cutList;
    }


    private List<TestEntity02> getDataList02(XSSFSheet sheet, int start, int end) {
        List<TestEntity02> cutList = new ArrayList<>();
        for (int j = start; j < end; j++) {
            // String a = XLSXUtils.getCellValue(sheet.getRow(j), 0);

            String b = XLSXUtils.getCellValue(sheet.getRow(j), 1);
            String c = XLSXUtils.getCellValue(sheet.getRow(j), 2);

            TestEntity02 testEntity02=new TestEntity02();
            testEntity02.setName(b);
            testEntity02.setAddress(c);
            cutList.add(testEntity02);
        }
        return cutList;
    }
}