package com.github.dawidstankiewicz.forum.security;

public class AccessRules {

    protected static final String[] FOR_EVERYONE = {
        "/error",
        "/users/**"
    };

    protected static final String[] FOR_AUTHORIZED_USERS = {
        "/user/**",
        "/topic/new/**",
        "/topic/delete/**",
        "/section/delete/**",
        "/section/new/**",
        "/post/**",
        "/myprofile/**"};

    protected static final String[] FOR_ADMINS = {"/admin/**",
        "/users/**",
        "/section/new"};

    protected static final String[] ADMINS_ROLES = {"HEAD_ADMIN",
        "ADMIN"};

}
