package com.utils;

public class Utils {
    public static boolean isNumeric(String str) {
        if (str == null) return false;
        if (str.length() < 1) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
