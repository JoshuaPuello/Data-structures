package com.company.exercises.strings;

import com.company.utils.Utils;

import java.util.ArrayList;

/**
 * Write a function that takes in two strings: a main string and a potential substring of
 * the main string. The function should return a version of the main string with every
 * instance of the substring in it wrapped between underscores. If two instances of the
 * substring in the main string overlap each other or sit side by side, the underscores
 * relevant to these two substrings should only appear on the far left of the left substring
 * and on the far right of the right substring. If the main string does not contain the
 * other string at all, return the main string intact.
 * ​
 * Sample input: "testthis is a testtest to see if testestest it works", "test"
 * Sample output: "_test_this is a _testtest_ to see if _testestest_ it works"
 */
public class UnderscorifySubstring {

    private static final int TEST_NAME_INDEX = 0;
    private static final int INPUT_INDEX = 1;
    private static final int SUBSTRING_INDEX = 2;
    private static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {

            ArrayList<Integer[]> locations =
                    collapse(getLocations(String.valueOf(input[INPUT_INDEX]),
                            String.valueOf(input[SUBSTRING_INDEX])));

            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    underscorify(String.valueOf(input[INPUT_INDEX]), locations),
                    String.valueOf(input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "this is a test to see if it works", "test", "this is a _test_ to see if it works" },
                { "Test 2", "test this is a test to see if it works", "test", "_test_ this is a _test_ to see if it works" },
                { "Test 3", "testthis is a test to see if it works", "test", "_test_this is a _test_ to see if it works" },
                { "Test 4", "testthis is a testest to see if testestes it works", "test", "_test_this is a _testest_ to see if _testest_es it works" },
                { "Test 5", "testthis is a testtest to see if testestest it works", "test", "_test_this is a _testtest_ to see if _testestest_ it works" }
        };
        return tests;
    }

    private static ArrayList<Integer[]> getLocations(String str, String substring) {
        ArrayList<Integer[]> locations = new ArrayList<Integer[]>();
        int startIdx = 0;
        while (startIdx < str.length()) {
            int nextIdx = str.indexOf(substring, startIdx);
            if (nextIdx != -1) {
                locations.add(new Integer[]{nextIdx, nextIdx + substring.length()});
                startIdx = nextIdx + 1;
            } else {
                break;
            }
        }
        return locations;
    }

    private static ArrayList<Integer[]> collapse(ArrayList<Integer[]> locations) {
        if (locations.size() == 0) {
            return locations;
        }
        ArrayList<Integer[]> newLocations = new ArrayList<Integer[]>();
        newLocations.add(locations.get(0));
        Integer[] previous = newLocations.get(0);
        for (int i = 1; i < locations.size(); i++) {
            Integer[] current = locations.get(i);
            if (current[0] <= previous[1]) {
                previous[1] = current[1];
            } else {
                newLocations.add(current);
                previous = current;
            }
        }
        return newLocations;
    }

    private static String underscorify(String str, ArrayList<Integer[]> locations) {
        int locationsIdx = 0;
        int stringIdx = 0;
        boolean inBetweenUnderscores = false;
        ArrayList<String> finalChars = new ArrayList<String>();
        int i = 0;
        while (stringIdx < str.length() && locationsIdx < locations.size()) {
            if (stringIdx == locations.get(locationsIdx)[i]) {
                finalChars.add("_");
                inBetweenUnderscores = !inBetweenUnderscores;
                if (!inBetweenUnderscores) {
                    locationsIdx++;
                }
                i = i == 1 ? 0 : 1;
            }
            finalChars.add(String.valueOf(str.charAt(stringIdx)));
            stringIdx += 1;
        }
        if (locationsIdx < locations.size()) {
            finalChars.add("_");
        } else if (stringIdx < str.length()) {
            finalChars.add(str.substring(stringIdx));
        }
        return String.join("", finalChars);
    }
}
