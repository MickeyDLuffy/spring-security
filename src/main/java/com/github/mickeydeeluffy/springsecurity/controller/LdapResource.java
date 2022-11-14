package com.github.mickeydeeluffy.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapResource {

    @GetMapping("/ldap")
    public String index() {
        return "index";
    }
}
