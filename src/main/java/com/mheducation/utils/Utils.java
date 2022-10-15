package com.mheducation.utils;

public class Utils {

    public static Integer extractDigitsFromString(String string) {
        if (string.isEmpty()) return null;
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }
}
