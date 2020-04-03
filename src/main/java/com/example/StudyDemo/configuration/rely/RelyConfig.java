package com.example.StudyDemo.configuration.rely;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-26
 * @since 1.0.0
 */
//@Configuration
//public class RelyConfig {
//
//
//    @Autowired
//    RelyB relyB;
//
//    @Autowired
//    RelyA relyA;
//
//    @Bean(name = "relyA")
//    public RelyA newRelyA() {
//        RelyA relyA = new RelyA(relyB);
////        RelyA relyA = new RelyA();
////        relyA.setRelyB(relyB);
//        return relyA;
//
//    }
//
//    @Bean(name = "relyB")
//    public RelyB newRelyB() {
//        RelyB relyB = new RelyB(relyA);
////        RelyB relyB = new RelyB();
////        relyB.setRelyC(relyA);
//        return relyB;
//    }
//}

//循环依赖报错， 使用@Autowired后可以避免循环依赖
//        ┌─────┐
//        |  relyA defined in class path resource [com/example/StudyDemo/configuration/BeanConfig.class]
//        ↑     ↓
//        |  relyB defined in class path resource [com/example/StudyDemo/configuration/BeanConfig.class]
//        ↑     ↓
//        └─────┘