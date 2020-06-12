package com.example.studyDemo;

import com.example.studyDemo.service.TestForAOPService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableAspectJAutoProxy
@SpringBootApplication
public class StudyDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StudyDemoApplication.class, args);
        //ConfigurableApplicationContext（ ApplicationContext）获取bean类型或名称获取
        TestForAOPService testForAOPService = (TestForAOPService) run.getBean("TestForAOPService");
        testForAOPService.login();

        //获取注解configuration配置的bean
//        ApplicationContext context = new AnnotationConfigApplicationContext(TestForBean.class);
//        TestEntity bean = context.getBean(TestEntity.class);
//        System.out.println(bean.getName());

//		Book book = (Book)run.getBean("book");
//		System.out.println("Book name = " + book.getBookName());
//
//        RelyA relyA =(RelyA) run.getBean("relyA");
//        System.out.println(relyA);
//
//        RelyB relyB=(RelyB)run.getBean("relyB") ;
//        System.out.println(relyB);
//
//        run.close();
    }

}
