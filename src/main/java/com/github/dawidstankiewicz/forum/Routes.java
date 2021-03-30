package com.github.dawidstankiewicz.forum;

public class Routes {

    public static final String REGISTRATION_CONFIRMATION = "registration/confirmation_sent";
    public static final String NEW_USER_FORM = "registration/new_user_form";

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
