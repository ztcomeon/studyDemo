package com.example.StudyDemo.service;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-25
 * @since 1.0.0
 */
public class TestService02 {




    public static void main(String[] args) {
//        test01();

//        test02();






    }

    private static void test02() {
        List<String> list = new ArrayList<>();
        list.add("微博");
        list.add("抖音");
        list.add("微信");
        System.out.println("before" + list);
//        Collections.sort(list);

        //自定义比较器，这里使用匿名内部类
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return -1;
//            }
//        });
        //使用lambda表达式写匿名内部类
//        Collections.sort(list, (o1, o2) -> -1);

        //使用java8的函数式排序，需要在学习一下
        Collections.sort(list, Comparator.comparing(String::hashCode).reversed());

        System.out.println("after" + list);
    }

    private static void test01() {
        List<String> list = new ArrayList<>();
        list.add("微博");
        list.add("抖音");
        list.add("微信");
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String element = it.next();
            System.out.println(element);
            //注意这里为翻车写法1，会报java.util.NoSuchElementException
            //at java.util.ArrayList$Itr.next
            //因为  在使用迭代器的时候注意 next()  方法在同一循环中不能出现俩次
//            if("抖音".equals(it.next())){
////                list.remove();//翻车写法2
//                it.remove();//正确写法
//            }

            if ("抖音".equals(element)) {
                it.remove();//正确写法
            }
        }

        System.out.println("之后" + list);
    }
}