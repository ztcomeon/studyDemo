package com.example.studyDemo;

import com.example.studyDemo.entity.JsonEntity;
import com.example.studyDemo.entity.TestEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        System.out.println(result + "====================class");


        // json字符串转Map数组List<Map<String,Object>>
        CollectionType collectionType2 = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Map.class);
        Object result2 = mapper.readValue(string2, collectionType2);
        System.out.println(result2 + "===============map");


        ArrayList arrayList = mapper.readValue(string2, ArrayList.class);
        System.out.println(arrayList + "================arraylist");

        LocalDate localDate = LocalDate.parse("2020-01-01");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format = localDate.format(timeFormatter);
        System.out.println(format);

    }

    @Test
    public void joinerListTest() throws Exception {

        List<String> lists = Lists.newArrayList("a", "b", "g", "8", "9");
        //Joiner连接器，joiner on就是将list用，连接转成字符串
        String result = Joiner.on(",").join(lists);
        System.out.println(result);
        //结果：a,b,g,8,9
    }

    @Test
    public void joinerListTest1() {
        List<String> lists = Lists.newArrayList("a", "b", "g", null, "8", "9");
        //Guava 对空指针有着严格的限制，如果传入的对象中包含空指针，Joiner 会直接抛出 NPE。
        String result = Joiner.on(",").join(lists);
        System.out.println(result);
    }

    @Test
    public void joinerListTest2() {
        List<String> lists = Lists.newArrayList("a", "b", "g", null, "8", "9");
        //skipNulls跳过null的
        String result = Joiner.on(",").skipNulls().join(lists);
        System.out.println(result);
        //a,b,g,8,9
    }

    @Test
    public void joinerListTest3() {
        List<String> lists = Lists.newArrayList("a", "b", "g", null, "8", "9");
        //userForNull将null变成指定的字符串
        String result = Joiner.on(",").useForNull("null").join(lists);
        System.out.println(result);
        //a,b,g,null,8,9
    }

    @Test
    public void withMapTest() {
        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1, "哈哈");
        maps.put(2, "压压");
        // joiner withKeyValueSeparator(String value)   map连接器，keyValueSeparator为key和value之间的分隔符
        String result = Joiner.on(",").withKeyValueSeparator(":").join(maps);
        System.out.println("result" + result);
        System.out.println("maps" + maps);
        // result    1:哈哈,2:压压
        // maps  {1=哈哈, 2=压压}
    }

    @Test
    public void splitterListTest() {
        String test = "34344,34,34,哈哈";
//        Splitter on 进行拆分  splitToList拆分成list
        List<String> list = Splitter.on(",").splitToList(test);
        System.out.println(list);
        // [34344, 34, 34, 哈哈]
    }

    @Test
    public void trimResultListTest() {
        String test = "  34344,34,34,哈哈 ";
        // Splitter trimResults 拆分去除前后空格
        List<String> lists = Splitter.on(",").trimResults().splitToList(test);
        System.out.println(lists);
    }

    @Test
    public void omitEmptyStringsTest() {
        String test = "  3434,434,34,,哈哈 ";
        // omitEmptyStrings 去除拆分出来的空字符串
        List<String> lists = Splitter.on(",").omitEmptyStrings().splitToList(test);
        System.out.println(lists);
    }

    @Test
    public void fixedLengthTest() {
        String test = "343443434哈哈";
        List<String> lists = Splitter.fixedLength(3).splitToList(test);
        System.out.println(lists);
    }

    @Test
    public void isTest() {
        String str = "12312,agg";
        // charMatcher is (Char char)给单一字符匹配
        // match匹配，matcher匹配器
        CharMatcher charMatcher1 = CharMatcher.is('g');
        // charMatcher  retainFrom(String s)  在字符序列中保留匹配字符，移除其他字符
        System.out.println(charMatcher1.retainFrom(str));
    }


    @Test
    public void charMatcherTest() {
        String str = "12312,agg  ";
        //两个匹配符,先匹配再操作
        CharMatcher charMatcher1 = CharMatcher.is('1');
        CharMatcher charMatcher2 = CharMatcher.is('2');
        //两个CharMatcher或操作
        CharMatcher charMatcher3 = charMatcher1.or(charMatcher2);
        System.out.println(charMatcher3.retainFrom(str));
    }

    @Test
    public void matchesAllOfTest() {
        String str = "12312,agg";
        CharMatcher charMatcher1 = CharMatcher.is('r');
        System.out.println(charMatcher1.matchesAllOf(str));
        System.out.println(charMatcher1.matchesAnyOf(str));
        System.out.println(charMatcher1.matchesNoneOf(str));
        System.out.println(charMatcher1.matches('p'));

    }

    @Test
    public void testForGuavaAndJDK() {
        //JDK
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        //guava
        List<String> lists = Lists.newArrayList("a", "b", "g", null, "8", "9");
        lists.add("test");
        List<String> lists2 = Lists.newArrayList();
        lists2.add("qq");
        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1,"kkk");
        System.out.println(lists);
        System.out.println(lists2);
        System.out.println(maps);
    }

    @Test
    public void testForHashCode() {
        TestEntity testEntity=new TestEntity();
        testEntity.setName("aaa");
        int hashCode = testEntity.hashCode();
        System.out.println(hashCode);
        // hashcode值 211853855
    }

}
