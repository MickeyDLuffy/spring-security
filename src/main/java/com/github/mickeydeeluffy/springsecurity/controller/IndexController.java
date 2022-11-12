package com.github.mickeydeeluffy.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public @ResponseBody String getIndex() {
        return "this is working";
    }

    @GetMapping("/admin")
    public @ResponseBody String adminName() {
        return "I am an Admin";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "I am an NORMAl";
    }
    @PostMapping("/success")
    public @ResponseBody String success() {
        return "Successfully logged in";
    }

    @PostMapping("/failure")
    public @ResponseBody String failure() {
        return "Failed logged in";
    }
}
