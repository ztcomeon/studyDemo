package com.example.studyDemo.service.impl;

import com.example.studyDemo.service.TestForAOPService;
import org.springframework.stereotype.Service;

@Service("TestForAOPService")
public class TestForAOPServiceImpl implements TestForAOPService {

    @Override
    public void login() {
        System.out.println("登录成功");
    }
}
