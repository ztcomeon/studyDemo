package com.example.StudyDemo.configuration.rely;

import com.example.StudyDemo.configuration.rely.RelyA;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-26
 * @since 1.0.0
 */
public class RelyB {

    private RelyA relyA;

    public RelyB(){}
    public RelyB(RelyA relyA) {
        System.out.println("BBB构造函数");
        this.relyA = relyA;
    }

    public RelyA getRelyA() {
        return relyA;
    }

    public void setRelyC(RelyA relyA) {
        this.relyA = relyA;
    }
}