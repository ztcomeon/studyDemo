package com.example.studyDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestForXD {

    @Test
    public void test1() {
        int i = testFinally();
        System.out.println(i);

        int j = testFinally2();
        System.out.println(j);
    }

    public int testFinally() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;

        } finally {
            a = 4;
        }
        return a;

    }

    public int testFinally2() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;

        } finally {
            a = 4;
            return a;//这里有return会直接返回
        }
    }

    //===============
    @Test
    public void test2() {
        String s1 = "xdclass";
        String s2 = s1 + ".net";//变量 + 常量 = 来自堆
        String s3 = "xdclass" + ".net";//常量 + 常量= 来自常量池
        System.out.println(s2 == "xdclass.net");//false
        System.out.println(s3 == "xdclass.net");//true

        //    javac编译可以对【字符串常量】直接相加的表达式进⾏优化
        // 如果需要第⼀个输出为true，只需要把变量改为常量即可 fianl String s1 = "xdclass";
        // （final关键字修饰后，变为了常量）
    }

    @Test
    public void test3() {

        Collections.synchronizedList(new ArrayList<String>());
        ArrayList<String> listForCopyOnWriteArrayList = new ArrayList<>(100);
        List<String> objects = new CopyOnWriteArrayList<>(listForCopyOnWriteArrayList);
        //CopyOnWriteArrayList实现原理及源码分析 https://www.cnblogs.com/chengxiao/p/6881974.html
        //    CopyOnWriteArrayList写的时候才复制，读的时候不复制，适合    读多写少    的场景
        // 原理：允许并发读，读操作是无锁的，性能较高。至于写操作，比如向容器中添加一个元素，
        //    则首先将当前容器复制一份，然后在新副本上执行写操作，结束之后再将原容器的引用指向新容器。（副本上有锁）
        //  优缺点分析：优点：比较适用于读多写少的并发场景，性能好，
        //              缺点：一是内存占用问题，二是无法保证实时性

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        System.out.println("---" + list.size());
        list.add("xdclass.net");
        System.out.println("---" + list.size());

    }

    @Test
    public void test4() {
        ConcurrentHashMap<String, String> ch = new ConcurrentHashMap();
        // 在JDK1.7版本中，ConcurrentHashMap的数据结构是由一个Segment数组和多个HashEntry组成
        // JDK1.8的实现已经摒弃了Segment的概念，而是直接用Node数组+链表+红黑树的数据结构来实现，并发控制使用
        // Synchronized和CAS来操作，整个看起来就像是优化过且线程安全的HashMap，
        // 虽然在JDK1.8中还能看到Segment的数据结构，但是已经简化了属性，只是为了兼容旧版本

        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            System.out.println(key);

            String value = entry.getValue();
            System.out.println(value);
        }
        Thread th = new Thread(() -> {
            System.out.println("d");
        });

        th.start();

        Thread th2=new Thread(new FutureTask<Object>(()->{
            System.out.println("dd");
            return "d";
        }));
        th2.start();

Executors.newFixedThreadPool(5);

    }
}
