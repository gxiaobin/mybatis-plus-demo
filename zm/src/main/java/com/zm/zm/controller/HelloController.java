package com.zm.zm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
/**
 * @author xx
 * @date 2019/4/15 15:34
 */
public class HelloController {

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
