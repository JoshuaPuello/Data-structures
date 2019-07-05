package com.company.utils;

public final class Utils {

    static String stringFormat = "%s has %s";

    public static void printAssertEquals(String testName, String s1, String s2) {
        final String result = String.format(stringFormat, testName, (s1.equals(s2) ? "passed" : "failed"));
        System.out.println("Utils.printAssertEquals | " + result);
    }

}
