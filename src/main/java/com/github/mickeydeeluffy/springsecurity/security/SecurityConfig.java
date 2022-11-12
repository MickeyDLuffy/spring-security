package com.github.mickeydeeluffy.springsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * This is meant for in memory authentication
         */
        auth.inMemoryAuthentication()
                .withUser("dluffy")
                .password(getEncoder().encode("juu"))
                .roles("USER")
                .and()
                .withUser("jhey")
                .password(getEncoder().encode("jhey"))
                .roles("ADMIN");

        /**
         * This is a jdbc authentication
         */

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(User.withUsername("dluffy").password(encodePassword("juu")).roles("USER"))
                .withUser(User.withUsername("jhey").password(encodePassword("jhey")).roles("ADMIN","USER"));
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Do the permissions in other of importance. For instance, ADMIN comes before USER, before ALL
         */

        /**
         * There are two approaches. The first one is using Lambda DSL(domain specific language). this approach uses
         * lambda expressions and need not use the 'and' to return the object
         */
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/admin").hasRole("ADMIN")
                                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                                .antMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .failureForwardUrl("/failure")
                                .successForwardUrl("/success")
                                .permitAll()
                );


/*
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                   .failureForwardUrl("/failure")
                   .successForwardUrl("/success")
        ;*/

    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String encodePassword(String password) {
        return getEncoder().encode(password);
    }
}
