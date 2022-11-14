package com.github.mickeydeeluffy.springsecurity.security;

import com.github.mickeydeeluffy.springsecurity.UserRepository;
import com.github.mickeydeeluffy.springsecurity.entity.User;
import com.github.mickeydeeluffy.springsecurity.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.security.Principal;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.
                findById(username)
                .map(User::toUserDetails)
                .orElseThrow(() -> {
                   log.error(String.format("User with username %s not found",username));
                   return new EntityNotFoundException(String.format("%s not found",username));
                });

    }
}
