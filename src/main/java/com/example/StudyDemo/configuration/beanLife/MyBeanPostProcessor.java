package com.example.StudyDemo.configuration.beanLife;

import com.example.StudyDemo.configuration.beanLife.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-26
 * @since 1.0.0
 */
@Component("myBeanPostProcessor")//自定义的BeanPostProcessor，需要加载到springboot中
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 容器加载的时候会加载一些其他的bean，会调用初始化前和初始化后方法
    // 这次只关注book(bean)的生命周期
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Book) {//限制只对Book bean起作用
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Book) {
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
        }
        return bean;
    }

}