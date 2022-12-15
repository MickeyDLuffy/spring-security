package com.github.mickeydeeluffy.springsecurity.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


@Builder
@Jacksonized
public record AuthResponse(String token) { }
