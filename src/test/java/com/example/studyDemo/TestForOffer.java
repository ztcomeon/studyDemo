package com.example.studyDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestForOffer {

    @Test
    public void test1() {
        // 在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，
        // 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
        // 例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字2或者3。

        // Integer[] integers = new Integer[]{2, 3, 1, 0, 2, 5, 3};
        Integer[] integers = new Integer[]{2, 3, 4, 5, 0, 1};
        int length = integers.length;
        //使用map，根据key不能重复，来判断
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Integer integer = map.get(integers[i]);
            if (integer == null) {
                map.put(integers[i], i);
            } else {
                list.add(integers[i]);
            }
        }
        System.out.println(list);

    }

    @Test
    public void test2() {
        //    请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，则输出“We%20are%20happy.”
        String s = "We are happy";
        StringBuilder sb = new StringBuilder(s);
        //获取新字符串的长度
        int newLength = sb.length();

        int oldLength = sb.length();
        for (int i = 0; i < oldLength; i++) {
            if (s.charAt(i) == ' ') {
                //新字符串的长度+2
                newLength += 2;
            }
        }

        sb.setLength(newLength);
        oldLength--;
        newLength--;
        while (oldLength != 0) {
            if (sb.charAt(oldLength) != ' ') {
                sb.setCharAt(newLength, sb.charAt(oldLength));
                oldLength--;
                newLength--;
            } else {
                sb.setCharAt(newLength--, '0');
                sb.setCharAt(newLength--, '2');
                sb.setCharAt(newLength--, '%');
                oldLength--;
            }
        }
        System.out.println(sb);

    }

    @Test
    public void test3() {
        //　输入一个链表的头结点，从尾到头反过来打印出每个结点的值。结点定义如下：
        class ListNode {
            int val;
            ListNode next = null;

            ListNode(int val) {
                this.val = val;
            }
        }
        ListNode ListNode1 = new ListNode(1);
        ListNode ListNode2 = new ListNode(2);
        ListNode ListNode3 = new ListNode(3);
        ListNode ListNode4 = new ListNode(4);
        ListNode ListNode5 = new ListNode(5);
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = ListNode5;

        //    思路：先进后出，可以用栈，或者递归
        Stack<ListNode> stack = new Stack<>();
        ListNode listNode = ListNode1;
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop().val);
        }

    }


}
