package com.dabing.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.hello:Test}")
    private String hello;

    @GetMapping("/hello")
    public String hello(){
        return "Hello,dabing!"+hello;
    }
}
