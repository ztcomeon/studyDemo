package com.example.StudyDemo;

import com.example.StudyDemo.entity.JsonEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyDemoApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        //对象转json
        ObjectMapper mapper = new ObjectMapper();
        JsonEntity jsonEntity = new JsonEntity();
        jsonEntity.setId("d");
        jsonEntity.setScore("12");
        jsonEntity.setContent("eeee");
        System.out.println(jsonEntity);
        //需要抛出或者捕获异常
        //对象转json字符串
        String reuslt = mapper.writeValueAsString(jsonEntity);
        System.out.println(reuslt + "========json");

        Map<String, Object> map = new HashMap<>();
        map.put("test", jsonEntity);
        //map转json字符串
        String result2 = mapper.writeValueAsString(map);
        System.out.println(result2 + "-------map");

        //数组转字符串
        JsonEntity jsonEntity1 = new JsonEntity();
        jsonEntity1.setId("11");
        jsonEntity1.setScore("2323");
        JsonEntity[] jarray = {jsonEntity, jsonEntity1};
        String result3 = mapper.writeValueAsString(jarray);
        System.out.println(result3 + "=============array");

        //json字符串转对象,这里需要转移一下json字符串中的冒号
        String string1 = "{\"id\":\"999\",\"score\":\"10\",\"content\":\"dsfsdf\"}";
        JsonEntity entity1 = mapper.readValue(string1, JsonEntity.class);
        System.out.println(entity1 + "---------------------entity");

    }

    @Test
    public void test1() throws Exception {

        String string1 = "{\"id\":\"999\",\"score\":\"10\",\"content\":\"dsfsdf\"}";

        //json字符串转Map
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(string1, Map.class);
        System.out.println(map);

        //json字符串转数组List<Object>
        String string2 = "[{\"id\":\"999\",\"score\":\"10\",\"content\":\"dsfsdf\"}," +
                "{\"id\":\"999\",\"score\":\"10\",\"content\":\"dsfsdf\"}," +
                "{\"id\":\"999\",\"score\":\"10\",\"content\":\"dsfsdf\"}]";

        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, JsonEntity.class);
        Object result = mapper.readValue(string2, collectionType);
        System.out.println(result+"====================class");


       // json字符串转Map数组List<Map<String,Object>>
        CollectionType collectionType2 = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class);
        Object result2 = mapper.readValue(string2, collectionType2);
        System.out.println(result2+"===============map");


        ArrayList arrayList = mapper.readValue(string2, ArrayList.class);
        System.out.println(arrayList+"================arraylist");

        LocalDate localDate=LocalDate.parse("2020-01-01");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format = localDate.format(timeFormatter);
        System.out.println(format);

    }

}
