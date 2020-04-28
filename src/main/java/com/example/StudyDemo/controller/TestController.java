package com.example.StudyDemo.controller;

import com.example.StudyDemo.entity.JsonEntity;
import com.example.StudyDemo.entity.TestEntity;
import com.example.StudyDemo.repository.TestRepository;
import com.example.StudyDemo.service.impl.TestImportExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/testController")
public class TestController {

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String test1(String string) {
        System.out.println("ddd");
        return "ok";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String test(@RequestBody String string) {
        System.out.println("ddd");
        System.out.println(string);
        ObjectMapper mapper = new ObjectMapper();

        try {
            //json字符串转Map
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class);
            List<Map<String, Object>> userMapList = mapper.readValue(string, listType);

            System.out.println(userMapList);
            List<JsonEntity> result = new ArrayList();

            for (Map map : userMapList) {
                JsonEntity json = new JsonEntity();
                Validate.isTrue(map.containsKey("id"), "工作类型必需有名称");
                json.setId(map.get("id").toString());
                Validate.isTrue(map.containsKey("score"), "工作类型必需有分数");
                json.setScore(map.get("score").toString());
                Validate.isTrue(map.containsKey("content"), "工作类型必需有工作内容");
                Object content = map.get("content");
                String s = mapper.writeValueAsString(content);
                List<Map<String, Object>> contentList = mapper.readValue(s, listType);
                for (Map contentMap : contentList) {
                    JsonEntity json1 = new JsonEntity();
                    Validate.isTrue(contentMap.containsKey("name"), "工作内容必需有名称");
                    json1.setId(contentMap.get("name").toString());
                    Validate.isTrue(contentMap.containsKey("score"), "工作内容必需有分数");
                    json1.setScore(contentMap.get("score").toString());
                    json1.setParentId(map.get("id").toString());
                    result.add(json1);
                }
                result.add(json);
            }

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @Autowired
    TestImportExcel testImportExcel;

    @RequestMapping(value = "/testImportExcel", method = RequestMethod.POST)
    public String testImportExcel() {
        testImportExcel.testImport();
        return "a";
    }

    @Autowired
    TestRepository testRepository;


    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        List<TestEntity> res = testRepository.findByName("成都环语化工有限公司");
        return res.toString();
    }
}