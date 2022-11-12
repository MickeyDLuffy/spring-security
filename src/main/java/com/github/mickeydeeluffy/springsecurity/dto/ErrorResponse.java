package com.github.mickeydeeluffy.springsecurity.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import java.util.List;


@Jacksonized
@Builder(toBuilder = true)
public record ErrorResponse(String message, String title, HttpStatus status, @Singular List<String> names) { }
