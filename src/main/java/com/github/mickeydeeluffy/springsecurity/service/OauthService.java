package com.github.mickeydeeluffy.springsecurity.service;

import com.github.mickeydeeluffy.springsecurity.dto.AuthRequest;
import com.github.mickeydeeluffy.springsecurity.dto.AuthResponse;
import com.github.mickeydeeluffy.springsecurity.security.UserDetailsServiceImp;
import com.github.mickeydeeluffy.springsecurity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImp userDetailsService;
    private final JwtUtil jwtUtil;
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException ex) {
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bad credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
       ;
        return AuthResponse.builder()
                .token( jwtUtil.generateToken(userDetails))
                .build();
    }
}
