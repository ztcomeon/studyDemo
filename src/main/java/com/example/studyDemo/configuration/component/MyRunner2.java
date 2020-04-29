package com.example.studyDemo.configuration.component;

import org.springframework.boot.CommandLineRunner;

//@Component
public class MyRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("============MyRunner2================");
    }
}

//实现CommandLineRunner或者实现AplicationRunner来实现启动时运行代码