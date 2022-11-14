package com.github.mickeydeeluffy.springsecurity.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private String roles;
    private boolean enabled;

    public static UserDetail toUserDetails(User user) {
        return UserDetail.builder()
                .username(user.username)
                .password(user.password)
                .isAccountNonExpired(user.enabled)
                .isEnabled(user.enabled)
                .authorities(Arrays.stream(user.roles.split(",")).map(SimpleGrantedAuthority::new).toList())
                .build();
    }
}
