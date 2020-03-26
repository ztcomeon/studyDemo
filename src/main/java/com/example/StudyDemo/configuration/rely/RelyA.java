package com.example.StudyDemo.configuration.rely;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-26
 * @since 1.0.0
 */
public class RelyA {

    private RelyB relyB;

    public RelyA(){}
    public RelyA(RelyB relyB) {
        System.out.println("aaaa的构造函数");
        this.relyB = relyB;
    }

    public RelyB getRelyB() {
        return relyB;
    }

    public void setRelyB(RelyB relyB) {
        this.relyB = relyB;
    }
}

