package com.example.StudyDemo.service.impl;

import com.example.StudyDemo.entity.TestEntity;
import com.example.StudyDemo.repository.TestRepository;
import com.example.StudyDemo.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020-03-25
 * @since 1.0.0
 */
@Service("TestService")
public class TestServiceImpl implements TestService, ApplicationContextAware {


    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    TestRepository testRepository;

    @Transactional
    @Override
    public TestEntity create(TestEntity testEntity) {

        return testRepository.save(testEntity);
    }
}