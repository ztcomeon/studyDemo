package com.example.studyDemo;

import com.example.studyDemo.entity.TestEntity;
import com.example.studyDemo.entity.TestEntity02;
import com.example.studyDemo.entity.User;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
public class Test1 {

  @Test
  public void test1() throws Exception {
    Integer i1 = 100;
    Integer i2 = 100;
    System.out.println(i1 == i2); // true

    // i3会翻译成 Integer i3= Integer.valueOf(128),valueOf方法里用到了缓存
    // 对-128到127之间的数进行了缓存
    Integer i3 = 128;
    Integer i4 = 128;
    Integer i5 = new Integer(128);

    //
    System.out.println(i3 == i4); // false
    // i5是堆内存中的对象，i3,i4是常量池中的对象
    System.out.println(i3 == i5); // false
  }

  @Test
  public void test2() throws Exception {
    Queue<Integer> integerpriorityQueue = new PriorityQueue();
    // 自然排序
    for (int i = 0; i < 7; i++) {
      Integer integer = new Integer(RandomUtils.nextInt(10, 100));
      System.out.println(integer);
      integerpriorityQueue.add(integer);
    }
    System.out.println("============");
    for (int i = 0; i < 7; i++) {
      Integer poll = integerpriorityQueue.poll();
      System.out.println(poll);
    }
  }

  @Test
  public void test3() throws Exception {
    // 给对象使用的时候，必需有比较器
    Comparator<TestEntity> comparator =
        new Comparator<TestEntity>() {
          @Override
          public int compare(TestEntity o1, TestEntity o2) {
            return o1.getAge() - o2.getAge();
          }
        };

    //        PriorityQueue是线程不安全的，PriorityBlockingQueue是线程安全的
    //        PriorityQueue通过二叉小顶堆实现
    Queue<TestEntity> objectQueue = new PriorityQueue<>(comparator);
    //        Queue<TestEntity> objectQueue2 =new PriorityBlockingQueue<>(100,comparator);

    for (int i = 0; i < 7; i++) {
      TestEntity testEntity = new TestEntity();
      testEntity.setName("eee" + RandomUtils.nextInt(0, 10));
      testEntity.setAge(RandomUtils.nextInt(0, 10));
      System.out.println(testEntity);
      objectQueue.add(testEntity); // 进队
    }

    System.out.println("============");
    for (int i = 0; i < 7; i++) {
      Object poll = objectQueue.poll(); // 出队
      System.out.println(poll);
    }
  }

  @Test
  public void test4() throws Exception {
    Map<String, Object> map = new HashMap<>();
    map.put("a", "ad");
    map.put("b", "wew");
    Set<Map.Entry<String, Object>> entries = map.entrySet();
    for (Map.Entry m : entries) {
      Object key = m.getKey();
      System.out.println(key);
      Object value = m.getValue();
      System.out.println(value);
    }
    BigDecimal bigDecimal = BigDecimal.valueOf(12.033);
    //    默认的权限能够方位当前类和同包下，子类不能访问
    //    protected（受保护的）可以访问当前类，同包和子类
    String[] ss = new String[4];
    ss[1] = "aaa";
    ss[2] = "bbb";
    ss[3] = "ccc";
  }

  @Test
  public void test5() {
    Optional<String> optional = Optional.ofNullable(null);

    if (optional.isPresent()) {
      String s = optional.get();
      System.out.println(s);
    }
  }

  @Test
  public void test6() {
    TestEntity02 testEntity02 = new TestEntity02();
    testEntity02.setName("dd");
    Optional<TestEntity02> optional = Optional.ofNullable(testEntity02);

    if (optional.isPresent()) {
      TestEntity02 s = optional.get();
      System.out.println(s);
    }
  }

  @Test
  public void test7() {
    List<String> list = Arrays.asList("aa", "bb");
    list.forEach(p -> System.out.println(Predicate.isEqual(p.startsWith("a"))));
    Function<String, Integer> fun = Integer::parseInt;
  }

  @Test
  public void test8() {
    List<String> list = Arrays.asList("springboot教程", "微服务教程", "并发编程", "压⼒测试", "架构课程");
    List<String> resultList =
        list.stream().map(obj -> "在⼩滴课堂学：" + obj).collect(Collectors.toList());
    System.out.println(resultList);
  }

  @Test
  public void test9() {
    List<String> list = Arrays.asList("springboot教程", "微服务教程", "并发编程", "压⼒测试", "架构课程");
    Set<String> resultList = list.stream().map(obj -> "在⼩滴课堂学：" + obj).collect(Collectors.toSet());
    System.out.println(resultList);
  }

  @Test
  public void test10() {
    List<User> list =
        Arrays.asList(
            new User("1", "ee", "12"), new User("2", "rr", "32"), new User("3", "ww", "16"));
    List<User> newlist =
        list.stream()
            .map(
                obj -> {
                  User u = new User(obj.getId(), obj.getName() + "NEW", obj.getAge());
                  return u;
                })
            .collect(Collectors.toList());
    System.out.println(newlist);
  }

  @Test
  public void test11() {
    List<String> list =
        Arrays.asList(
            "springboot", "springcloud", "redis", "git", "netty", "java", "html", "docker");

    list = list.stream().sorted(Comparator.comparing(s -> s.length())).collect(Collectors.toList());
    System.out.println(list);

    list =
        list.stream()
            .sorted(Comparator.comparing(s -> s.length(), Comparator.reverseOrder()))
            .collect(Collectors.toList());
    System.out.println(list);
  }

  @Test
  public void test12() {
    // 顺序输出
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    numbers.stream().forEach(System.out::println);
    // 并⾏乱序输出
    List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    numbers1.parallelStream().forEach(System.out::println);
  }
}
