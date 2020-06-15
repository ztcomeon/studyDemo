package com.example.studyDemo.configuration.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-06-12
 * @since 1.0.0
 */
public class WebMvcConfig implements WebMvcConfigurer {

  // extends WebMvcConfigurerAdapter 过时 使用 implements WebMvcConfigurer 代替
  // @Override
  // public void addResourceHandlers(ResourceHandlerRegistry registry) {
  //     super.addResourceHandlers(registry);
  // }

  @Value("${img.win_location}")
  private String win_location;

  @Value("${img.linux_location}")
  private String linux_location;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    if (registry.hasMappingForPattern("/static/**")) {
      String property = System.getProperty("os.name");
      if (property.startsWith("Win")) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:" + win_location);
      } else {
        registry.addResourceHandler("/static/**").addResourceLocations("file:" + linux_location);
      }
    }
  }

  //    过时的方法
  //   if (!registry.hasMappingForPattern("/static/**")) {
  //     String property = System.getProperty("os.name");
  //     if (property.startsWith("Win")) {
  //       registry.addResourceHandler("/static/**").addResourceLocations("file:" + win_location);
  //     } else {
  //       registry.addResourceHandler("/static/**").addResourceLocations("file:" + linux_location);
  //     }
  //   }
  //   super.addResourceHandlers(registry);
  // }
}
