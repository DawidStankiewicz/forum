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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private static final String USERS_BY_USERNAME_QUERY =
        "select username, password, active from users where username=?";
    
    private static final String AUTHORIZES_BY_USERNAME_QUERY =
        "select users.username, concat('ROLE_', roles.name) from users, roles, rolesofusers "
                + "where users.username=? and users.iduser=forum.rolesofusers.iduser and rolesofusers.idrole=roles.idrole";
    
    private static final String[] PAGES_ONLY_FOR_AUTHORIZED_USERS = { "/user/**" };
    
    private static final String[] LIST_OF_PAGES_ONLY_FOR_ADMINS = { "/admin/**",
                                                                    "/users/**" };
    
    private static final String[] LIST_OF_AUTHORIZED_ROLES = { "USER",
                                                               "ADMIN" };
    
    private static final String[] LIST_OF_ADMINS_ROLES = { "HEAD_ADMIN",
                                                           "ADMIN" };
    
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").and().logout().and().authorizeRequests().antMatchers(
                PAGES_ONLY_FOR_AUTHORIZED_USERS).hasAnyRole(LIST_OF_AUTHORIZED_ROLES).antMatchers(
                        LIST_OF_PAGES_ONLY_FOR_ADMINS).hasAnyRole(LIST_OF_ADMINS_ROLES);
        
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                USERS_BY_USERNAME_QUERY).authoritiesByUsernameQuery(AUTHORIZES_BY_USERNAME_QUERY)
                .passwordEncoder(passwordEncoder);
    }
}
