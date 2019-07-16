package com.company.utils;

import java.util.ArrayList;
import java.util.List;

public final class Utils {

    static String stringFormat = "%s has %s";

    public static void printAssertEquals(String testName, String s1, String s2) {
        final String result = String.format(stringFormat, testName, (s1.equals(s2) ? "passed \u2611" : "failed \u2612"));
        System.out.println("Utils.printAssertEquals | " + result);
    }

    public static void printAssertEquals(String testName, int num1, int num2) {
        final String result = String.format(stringFormat, testName, num1 == num2 ? "passed \u2611" : "failed \u2612");
        System.out.println("Utils.printAssertEquals | " + result);
    }

    public static void printAssertEquals(String testName, boolean bool1, boolean bool2) {
        final String result = String.format(stringFormat, testName, bool1 == bool2 ? "passed \u2611" : "failed \u2612");
        System.out.println("Utils.printAssertEquals | " + result);
    }

    public static void printAssertEquals(String testName, List<Integer[]> list1, List<Integer[]> list2) {
        boolean equals = true;
        for (int i = 0; i < list1.size(); i++) {
            for (int i1 = 0; i1 < list1.get(i).length; i1++) {
                if (!list1.get(i)[i1].equals(list2.get(i)[i1])) equals = false;
            }
        }
        final String result = String.format(stringFormat, testName, equals ? "passed \u2611" : "failed \u2612");
        System.out.println("Utils.printAssertEquals | " + result);
    }
}
