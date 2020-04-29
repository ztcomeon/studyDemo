package com.example.studyDemo.service.impl;

import com.example.studyDemo.common.utils.XLSXUtils;
import com.example.studyDemo.entity.TestEntity;
import com.example.studyDemo.repository.TestRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-04-27
 * @since 1.0.0
 */
@Service("TestImportExcel")
public class TestImportExcel {

    @Autowired
    TestRepository testRepository;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void testImport() {
        try {
            XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(new File("E:\\0422-all-2.xlsx")));
            XSSFSheet sheet = book.getSheetAt(0);
            int len = sheet.getLastRowNum();//读出总行数
            if (len > 0) {
                int threadNum = Runtime.getRuntime().availableProcessors() * 2; // 设置可用线程数
                int dataSize = len; // 待处理总数
                int threadSize = (dataSize / threadNum) == 0 ? 1 : dataSize / threadNum; // 线程分段处理数(保证线程执行的数量最低为1，避免除数为0)
                boolean special = dataSize % threadSize == 0; // 判断余数是否为0，即是否整除
                List<TestEntity> cutList = null;
                for (int i = 0; i < threadNum; i++) {
                    if (special) {//整除时
                        if ((i + 1) * threadSize > dataSize) {
                            break;
                        } else {
                            cutList = getDataList(sheet, threadSize, threadSize * (i + 1));
                        }

                    } else {//非整除时
                        if ((i + 1) == threadNum) {
                            cutList = getDataList(sheet, threadSize * i, dataSize);
                        } else {
                            cutList = getDataList(sheet, threadSize * i, threadSize * (i + 1));
                        }
                    }
                    List<TestEntity> finalCutList = cutList;
                    threadPoolTaskExecutor.execute(() -> {
                        System.out.println(Thread.currentThread().getName());
                        testRepository.saveAll(finalCutList);
                    });
                }
            }
//====================================
//            List<TestEntity> list = new ArrayList<>();
//            for (int i = 1; i <= len; i++) {
//
//                TestEntity testEntity = new TestEntity();
//                String a = XLSXUtils.getCellValue(sheet.getRow(i), 0);
//                testEntity.setName(a);
//                list.add(testEntity);
//            }
//
//            if (null != list && list.size() != 0) {
//                int threadNum = Runtime.getRuntime().availableProcessors() * 2; // 设置可用线程数
//                int dataSize = list.size(); // 待处理总数
//                int threadSize = (dataSize / threadNum) == 0 ? 1 : dataSize / threadNum; // 线程分段处理数(保证线程执行的数量最低为1，避免除数为0)
//                boolean special = dataSize % threadSize == 0; // 判断余数是否为0，即是否整除
//                List<TestEntity> cutList = null;
//                for (int i = 0; i < threadNum; i++) {
//                    if (special) {
//                        if ((i + 1) * threadSize > dataSize) {
//                            break;
//                        } else {
//                            cutList = list.subList(threadSize * i, threadSize * (i + 1));
//                        }
//                    } else {
//                        if ((i + 1) == threadNum) {
//                            cutList = list.subList(threadSize * i, dataSize);
//                        } else {
//                            cutList = list.subList(threadSize * i, threadSize * (i + 1));
//                        }
//                    }
//                    List<TestEntity> finalCutList = cutList;
//                    pool.execute(() -> {
//                        System.out.println(thread.currentThread().getName());
//                        testRepository.saveAll(finalCutList);
//                    });
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
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
}