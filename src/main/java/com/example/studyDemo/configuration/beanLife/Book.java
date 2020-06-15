package com.example.studyDemo.configuration.beanLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-26
 * @since 1.0.0
 */
@Component
public class Book implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String bookName;

    public Book() {
        System.out.println("Book Initializing ");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Book.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("Book.setBeanName invoke , name=" + s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Book.destory invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Book.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Book.setApplicationContext invoke");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("setBookName: Book name has set.");
    }

    // 自定义初始化方法
    public void myPostConstruct() {
        System.out.println("Book.myPostConstruct invoke--------");

    }

    @PostConstruct
    public void springPostConstruct() {
        System.out.println("@PostConstruct");
    }

    // 自定义销毁方法
    public void myPreDestory() {
        System.out.println("Book.myPreDestory invoke-------");
    }


    @PreDestroy
    public void springPreDestory() {
        System.out.println("@PreDestory");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("------inside finalize-----");
    }

// bean的生命周期，这里是主要的一些，完整的可以在去查资料
//    实现了：三个aware接口 BeanNameAware, BeanFactoryAware, ApplicationContextAware,设置bean名称,工厂,上下文
//            两个bean的接口 InitializingBean, DisposableBean,初始化和销毁
//            后置处理器BeanPostProcessor   初始化方法的前后调用后置处理器
//
//    Book Initializing                     bean启动，BeanConfig类  new出一个实例,实例化
//    setBookName: Book name has set.       设置bean的属性，
//    Book.setBeanName invoke , name=book   BeanNameAware接口  @bean注解中name属性，设置bean的名称
//    Book.setBeanFactory invoke            BeanFactoryAware接口，调用setBeanFactory()方法，将BeanFactory容器实例传入
//    Book.setApplicationContext invoke     ApplicationContextAware接口 setApplicationContext()方法，将bean所在应用上下文引用传入进来
//    MyBeanPostProcessor.postProcessBeforeInitialization     BeanPostProcessor接口(后置处理器)，调用postProcessBeforeInitialization()方法。
//    @PostConstruct                        @PostConstruct 注解，
//    Book.afterPropertiesSet invoke        InitializingBean接口，调用他们的afterPropertiesSet()方法
//    Book.myPostConstruct invoke--------   initMethod指定的方法，自定义初始化方法
//    MyBeanPostProcessor.postProcessAfterInitialization      BeanPostProcessor接口(后置处理器)，调用postProcessAfterInitialization()方法
//    @PreDestory                            @PreDestory 注解
//    Book.destory invoke                   DisposableBean接口，调用destory()接口方法
//    Book.myPreDestory invoke-------        destroyMethod指定的方法，自定义的销毁方法
//
//    https://www.cnblogs.com/javazhiyin/p/10905294.html  参考链接

}