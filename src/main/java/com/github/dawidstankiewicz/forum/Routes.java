package com.github.dawidstankiewicz.forum;

public class Routes {

    private final static String REDIRECT = "redirect:";

    public static String loginPage() {
        return "/login";
    }

    public static String redirectLoginPage() {
        return REDIRECT + loginPage();
    }

    public static String redirectNewUserFormPage() {
        return REDIRECT + newUserFormPage();
    }

    private static String newUserFormPage() {
        return "/new-user";
    }
}
