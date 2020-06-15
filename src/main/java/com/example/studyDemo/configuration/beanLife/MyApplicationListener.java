package com.example.studyDemo.configuration.beanLife;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-15
 * @since 1.0.0
 */
public class MyApplicationListener implements ApplicationListener {

  @Override
  public void onApplicationEvent(ApplicationEvent applicationEvent) {
    System.out.println("MyApplicationListener onApplicationEvent方法执行");
  }
}
