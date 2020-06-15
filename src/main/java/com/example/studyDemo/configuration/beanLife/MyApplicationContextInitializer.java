package com.example.studyDemo.configuration.beanLife;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-15
 * @since 1.0.0
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
    System.out.println("MyApplicationContextInitializer initialize方法执行");
  }
}
