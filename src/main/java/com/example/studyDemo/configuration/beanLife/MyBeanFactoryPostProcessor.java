package com.example.studyDemo.configuration.beanLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-15
 * @since 1.0.0
 */
@Component("myBeanFactoryPostProcessor")
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory方法执行");
  }
}
