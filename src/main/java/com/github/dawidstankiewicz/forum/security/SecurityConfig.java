package com.github.dawidstankiewicz.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.github.dawidstankiewicz.forum.security.AccessRules.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ForumUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CsrfTokenRepository csrfTokenRepository;

    public SecurityConfig(ForumUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, CsrfTokenRepository csrfTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureAccessRules(http);
        configureLoginForm(http);
        configureLogout(http);
        configureRememberMe(http);
        configureCsrf(http);
        configureEncodingFilter(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    private void configureAccessRules(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(FOR_AUTHORIZED_USERS).authenticated()
            .antMatchers(FOR_ADMINS).hasAnyAuthority(ADMINS_ROLES)
            .antMatchers(FOR_EVERYONE).permitAll();
    }

    private void configureLoginForm(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/login")
            .permitAll();
    }

    private void configureLogout(HttpSecurity http) throws Exception {
        http.logout()
            .permitAll();
    }

    private void configureRememberMe(HttpSecurity http) throws Exception {
        http.rememberMe()
            .tokenValiditySeconds(2419200)
            .key("forum-key");
    }

    private void configureCsrf(HttpSecurity http) throws Exception {
        http.csrf()
            .csrfTokenRepository(csrfTokenRepository);
    }

    private void configureEncodingFilter(HttpSecurity http) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);
    }
}
