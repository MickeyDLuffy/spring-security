package com.github.mickeydeeluffy.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public  String getIndex() {
        return "this is working";
    }

    @GetMapping("/admin")
    public  String adminName() {
        return "I am an Admin";
    }

    @GetMapping("/user")
    public String user() {
        return "I am an NORMAl";
    }
    @PostMapping("/success")
    public  String success() {
        return "Successfully logged in";
    }

    @PostMapping("/failure")
    public String failure() {
        return "Failed logged in";
    }
}
