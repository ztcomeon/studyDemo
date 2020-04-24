package com.example.StudyDemo.configuration.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner  implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============MyRunner=========");
    }
}
//实现CommandLineRunner或者实现AplicationRunner来实现启动时运行代码