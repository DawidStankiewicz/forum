package com.github.dawidstankiewicz.forum.user;

import com.github.dawidstankiewicz.forum.exception.ForumException;

public enum Gender {
    MALE(0),
    FEMALE(1);

    private static String[] maleSymbols = {"m", "male"};
    private static String[] femaleSymbols = {"f", "female"};

    Gender(int index) {
    }

    Gender(String value) {
        parse(value);
    }

    public static Gender parse(String value) {
        value = value.trim();
        for (String m : maleSymbols) {
            if (m.equalsIgnoreCase(value)) {
                return MALE;
            }
        }

        for (String f : femaleSymbols) {
            if (f.equalsIgnoreCase(value)) {
                return FEMALE;
            }
        }
        throw new ForumException(ForumException.ErrorCode.INVALID_GENDER);
    }

}