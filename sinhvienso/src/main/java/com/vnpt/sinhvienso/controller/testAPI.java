package com.vnpt.sinhvienso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class testAPI {
    @GetMapping("")
    public String getStarted(){
        return "Hello";
    }
}
