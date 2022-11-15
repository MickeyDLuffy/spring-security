package com.github.mickeydeeluffy.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Properties;

@RestController
public class LdapResource {

    @GetMapping("/ldap")
    public String index() {

        return "index";
    }
}
