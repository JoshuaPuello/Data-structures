package com.company.exercises.strings;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * You are given two non-empty strings. The first one is a pattern consisting of only "x"s
 * and / or "y"s; the other one is a normal string of alphanumeric characters. Write a
 * function that checks whether or not the normal string matches the pattern. A string S0
 * is said to match a pattern if replacing all "x"s in the pattern with some string S1 and
 * replacing all "y"s in the pattern with some string S2 yields the same string S0. If the
 * input string does not match the input pattern, return an empty array; otherwise, return
 * an array holding the representations of "x" and "y" in the normal string, in that order.
 * If the pattern does not contain any "x"s or "y"s, the respective letter should be represented
 * by an empty string in the final array that you return. Assume that there will never be
 * more than one pair of strings S1 and S2 that appropriately represent "x" and "y" in the
 * input string.
 * ​
 * Sample input: "xxyxxy", "gogopowerrangergogopowerranger"
 * Sample output: ["go", "powerranger"]
 */
public class PatternMatcher {

    public static final int TEST_NAME_INDEX = 0;
    public static final int PATTERN_INDEX = 1;
    public static final int INPUT_INDEX = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(patternMatcher(String.valueOf(input[PATTERN_INDEX]),
                            String.valueOf(input[INPUT_INDEX]))),
                    Arrays.toString((String[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "xyxy", "abab", new String[] {"a", "b"} },
                { "Test 2", "yxyx", "abab", new String[] {"b", "a"} },
                { "Test 3", "yxx", "yomama", new String[] {"ma", "yo"} },
                { "Test 4", "xxyxxy", "gogopowerrangergogopowerranger", new String[] {"go", "powerranger"} },
                { "Test 5", "xxyxyy", "testtestwrongtestwrongtest", new String[] {} },
        };
        return tests;
    }

    private static String[] patternMatcher(String pattern, String str) {
        if (pattern.length() > str.length()) {
            return new String[] {};
        }
        char[] newPattern = getNewPattern(pattern);
        boolean didSwitch = newPattern[0] != pattern.charAt(0);
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        counts.put('x', 0);
        counts.put('y', 0);
        int firstYPos = getCountsAndFirstYPos(newPattern, counts);
        if (counts.get('y') != 0) {
            for (int lenOfX = 1; lenOfX < str.length(); lenOfX++) {
                double lenOfY = ((double)str.length() - (double)lenOfX * (double)counts.get('x')) / (double)counts.get('y');
                if (lenOfY <= 0 || lenOfY % 1 != 0) {
                    continue;
                }
                int yIdx = firstYPos * lenOfX;
                String x = str.substring(0, lenOfX);
                String y = str.substring(yIdx, yIdx + (int)lenOfY);
                String potentialMatch = buildPotentialMatch(newPattern, x, y);
                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[] {y, x} : new String[] {x, y};
                }
            }
        } else {
            double lenOfX = str.length() / counts.get('x');
            if (lenOfX % 1 == 0) {
                String x = str.substring(0, (int)lenOfX);
                String potentialMatch = buildPotentialMatch(newPattern, x, "");
                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[] {"", x} : new String[] {x, ""};
                }
            }
        }
        return new String[] {};
    }

    private static char[] getNewPattern(String pattern) {
        char[] patternLetters = pattern.toCharArray();
        if (pattern.charAt(0) == 'x') {
            return patternLetters;
        }
        for (int i = 0; i < patternLetters.length; i++) {
            if (patternLetters[i] == 'x') {
                patternLetters[i] = 'y';
            } else {
                patternLetters[i] = 'x';
            }
        }
        return patternLetters;
    }

    private static int getCountsAndFirstYPos(char[] pattern, HashMap<Character, Integer> counts) {
        int firstYPos = -1;
        for (int i = 0; i < pattern.length; i++) {
            char c = pattern[i];
            counts.put(c, counts.get(c) + 1);
            if (c == 'y' && firstYPos == -1) {
                firstYPos = i;
            }
        }
        return firstYPos;
    }

    private static String buildPotentialMatch(char[] pattern, String x, String y) {
        StringBuilder potentialMatch = new StringBuilder();
        for (char c : pattern) {
            if (c == 'x') {
                potentialMatch.append(x);
            } else {
                potentialMatch.append(y);
            }
        }
        return potentialMatch.toString();
    }
}
