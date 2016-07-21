/**
 * Created by Dawid Stankiewicz on 10.07.2016
 */
package com.github.szczypioreg.forum.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").and().logout().and().authorizeRequests()
                .antMatchers("/user/**", "/users").hasAnyRole("USER", "ADMIN");
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, active from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, concat('ROLE_', role) from users where username=?")
                .passwordEncoder(passwordEncoder);
    }
}
