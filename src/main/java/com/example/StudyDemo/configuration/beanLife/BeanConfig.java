package com.example.StudyDemo.configuration.beanLife;

import com.example.StudyDemo.configuration.beanLife.Book;
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
@Configuration
public class BeanConfig {


    //springboot装配bean的方式
    @Bean(name = "book", initMethod = "myPostConstruct", destroyMethod = "myPreDestory")
    public Book newBook() {
        Book book = new Book();//创建一个bean

        //实例化完成之后开始执行这个方法，并返回给springboot管理
        book.setBookName("thingking in java");//设置bean的属性
        return book;

    }

}