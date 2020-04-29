package com.example.studyDemo.configuration.rely;

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
//        |  relyA defined in class path resource [com/example/studyDemo/configuration/BeanConfig.class]
//        ↑     ↓
//        |  relyB defined in class path resource [com/example/studyDemo/configuration/BeanConfig.class]
//        ↑     ↓
//        └─────┘