package com.example.studyDemo;

import com.example.studyDemo.entity.*;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-25
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2 {

  @Test
  public void test1() {
    String collect = Stream.of("aa", "bb", "cc").collect(Collectors.joining(",", "[", "]"));
    System.out.println(collect);

    List<String> list = Arrays.asList("java", "springboot", "HTML5", "nodejs", "CSS3");
    Map<Boolean, List<String>> booleanListMap =
        list.stream().collect(Collectors.partitioningBy(obj -> obj.length() > 4));
    System.out.println(booleanListMap);

    List<Student> students =
        Arrays.asList(
            new Student("⼴东", 23),
            new Student("⼴东", 24),
            new Student("⼴东", 23),
            new Student("北京", 22),
            new Student("北京", 20),
            new Student("北京", 20),
            new Student("海南", 25));
    Map<String, List<Student>> studentMap1 =
        students.stream().collect(Collectors.groupingBy(obj -> obj.getProvince()));
    System.out.println(studentMap1);

    Map<String, Long> studentMap2 =
        students.stream()
            .collect(Collectors.groupingBy(Student::getProvince, Collectors.counting()));
    studentMap2.forEach((key, value) -> System.out.println(key + "省人数有：：" + value));
    System.out.println(studentMap2);

    IntSummaryStatistics studentSummarizing =
        students.stream().collect(Collectors.summarizingInt(Student::getNumber));
    System.out.println("最大值:" + studentSummarizing.getMax());
    System.out.println("最小值:" + studentSummarizing.getMin());
    System.out.println("人数:" + studentSummarizing.getCount());
    System.out.println("平均值:" + studentSummarizing.getAverage());
    System.out.println("总和:" + studentSummarizing.getSum());
  }

  @Test
  public void test2() {
    // 需求描述：电商订单数据处理，根据下⾯的list1和list2 各10个订单
    // 统计出同时被两个⼈购买的商品列表(交集)
    // 统计出两个⼈购买商品的差集
    // 统计出全部被购买商品的去重并集
    // 统计两个⼈的分别购买订单的平均价格
    // 统计两个⼈的分别购买订单的总价格

    // 总价 35
    List<VideoOrder> videoOrders1 =
        Arrays.asList(
            new VideoOrder("20190242812", "springboot教程", 3),
            new VideoOrder("20194350812", "微服务SpringCloud", 5),
            new VideoOrder("20190814232", "Redis教程", 9),
            new VideoOrder("20190523812", "⽹页开发教程", 9),
            new VideoOrder("201932324", "百万并发实战Netty", 9));

    // 总价 54
    List<VideoOrder> videoOrders2 =
        Arrays.asList(
            new VideoOrder("2019024285312", "springboot教程", 3),
            new VideoOrder("2019081453232", "Redis教程", 9),
            new VideoOrder("20190522338312", "⽹页开发教程", 9),
            new VideoOrder("2019435230812", "Jmeter压⼒测试", 5),
            new VideoOrder("2019323542411", "Git+Jenkins持续集成", 7),
            new VideoOrder("2019323542424", "Idea全套教程", 21));

    // 交集
    List<VideoOrder> result1 =
        videoOrders1.stream().filter(videoOrders2::contains).collect(Collectors.toList());
    System.out.println("交集" + result1);

    // 差集1
    List<VideoOrder> result2 =
        videoOrders1.stream()
            .filter(obj -> !videoOrders2.contains(obj))
            .collect(Collectors.toList());
    System.out.println("差集1" + result2);

    //  差集2
// videoOrders2.stream()

  }


  @Test
  public void test3() {}
}
