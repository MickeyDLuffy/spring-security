package com.github.mickeydeeluffy.springsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest {
    private String username;
    private String password;
}
