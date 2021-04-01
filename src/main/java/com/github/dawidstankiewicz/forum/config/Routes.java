package com.github.dawidstankiewicz.forum.config;

public class Routes {

    private final static String REDIRECT = "redirect:";

    public static final String REGISTRATION_CONFIRMATION = "registration/confirmation_sent";
    public static final String NEW_USER_FORM = "registration/new_user_form";

    private static final String ADMIN_PREFIX = "/a/";

    public static final String ADMIN_SECTIONS_PANEL = ADMIN_PREFIX + "/sections";


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

    public class Views {
        private static final String ADMIN_PREFIX = "admin/";

        public static final String ADMIN_SECTIONS_PANEL = ADMIN_PREFIX + "sections";

    }
}
