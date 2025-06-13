package com.vnpt.sinhvienso.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class testAPI {
    @GetMapping("/test-hello")
    public String getStarted(){
        return "Hello";
    }
}
