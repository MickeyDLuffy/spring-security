package com.github.mickeydeeluffy.springsecurity.controller;

import com.github.mickeydeeluffy.springsecurity.dto.AuthRequest;
import com.github.mickeydeeluffy.springsecurity.dto.AuthResponse;
import com.github.mickeydeeluffy.springsecurity.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OauthController {
    private final OauthService oauthService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest authRequest) {
       return ResponseEntity.ok(oauthService.authenticate(authRequest));
    }

}
