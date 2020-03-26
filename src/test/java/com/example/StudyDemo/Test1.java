package com.example.StudyDemo;

import com.example.StudyDemo.entity.TestEntity;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.Query;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

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
        System.out.println(i1 == i2);//true

        //i3会翻译成 Integer i3= Integer.valueOf(128),valueOf方法里用到了缓存
        //对-128到127之间的数进行了缓存
        Integer i3 = 128;
        Integer i4 = 128;
        Integer i5 = new Integer(128);


        //
        System.out.println(i3 == i4);//false
        //i5是堆内存中的对象，i3,i4是常量池中的对象
        System.out.println(i3 == i5);//false

    }

    @Test
    public void test2() throws Exception {
        Queue<Integer> integerpriorityQueue = new PriorityQueue();
        //自然排序
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
        //给对象使用的时候，必需有比较器
        Comparator<TestEntity> comparator = new Comparator<TestEntity>() {
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
            objectQueue.add(testEntity);//进队
        }

        System.out.println("============");
        for (int i = 0; i < 7; i++) {
            Object poll = objectQueue.poll();//出队
            System.out.println(poll);
        }
    }


}