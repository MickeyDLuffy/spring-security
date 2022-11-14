package com.github.mickeydeeluffy.springsecurity.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Jacksonized
@Builder
@Data
public class UserDetail implements UserDetails {
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isEnabled;
    private @Singular List<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
