package com.example.studyDemo.controller;

import com.example.studyDemo.service.TestForAOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestForAOPController")
public class TestForAOPController {

    @Autowired
    TestForAOPService testForAOPService;

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String test1(String string, String stringb) {
        System.out.println("11111");
        testForAOPService.login();
        System.out.println("11111");
        System.out.println("11111");
        System.out.println("11111");
        return string + "===" + stringb;
    }
}
