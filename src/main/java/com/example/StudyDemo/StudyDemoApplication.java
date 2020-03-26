package com.example.StudyDemo;

import com.example.StudyDemo.configuration.rely.RelyA;
import com.example.StudyDemo.configuration.rely.RelyB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StudyDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StudyDemoApplication.class, args);
//		Book book = (Book)run.getBean("book");
//		System.out.println("Book name = " + book.getBookName());

        RelyA relyA =(RelyA) run.getBean("relyA");
        System.out.println(relyA);

        RelyB relyB=(RelyB)run.getBean("relyB") ;
        System.out.println(relyB);

        run.close();
    }

}
