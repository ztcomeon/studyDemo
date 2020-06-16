package com.example.studyDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
public class TestForJava8 {

    @Test
    public void test1() {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(4);
        Long aLong = list.stream().collect(Collectors.counting());
        System.out.println(aLong);
    }

}