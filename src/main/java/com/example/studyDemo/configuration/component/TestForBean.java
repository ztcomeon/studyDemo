package com.example.studyDemo.configuration.component;

import com.example.studyDemo.entity.TestEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@ComponentScans(value = {
        @ComponentScan(value = "com.example.studyDemo")

})
@Component
public class TestForBean {

    @Bean
    public TestEntity getTestEntity(){
        TestEntity testEntity = new TestEntity();
        testEntity.setName("forTest");
        return testEntity;
    }
}
