package com.company.exercises.strings;

import com.company.utils.Utils;

import java.util.HashMap;

/**
 * Write a function that takes in a string and that returns its longest substring without 
 * duplicate characters. Assume that there will only be one longest substring without 
 * duplication.
 *
 * Sample input: "clementisacap"
 * Sample output: "mentisac"
 */
public class LongestSubstringWithoutDuplication {

    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    longestSubstringWithoutDuplication(String.valueOf(input[INPUT_INDEX])),
                    String.valueOf(input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "a", "a" },
                { "Test 2", "abc", "abc" },
                { "Test 3", "abcb", "abc" },
                { "Test 4", "abcdeabcdefc", "abcdef" },
                { "Test 5", "abccdeaabbcddef", "cdea" },
                { "Test 6", "abacacacaaabacaaaeaaafa", "bac" },
                { "Test 7", "abcdabcef", "dabcef" },
                { "Test 8", "clementisacap", "mentisac" },
                { "Test 9", "clementisanarm", "mentisa" }
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity 0(min(n,a))
     */
    public static String longestSubstringWithoutDuplication(String str) {

        int[] longest = { 0,1 };
        int startIndex = 0;
        HashMap<Character, Integer> lastSeen = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
          char c = str.charAt(i);
          if (lastSeen.containsKey(c)) {
            startIndex = Math.max(startIndex, lastSeen.get(c) + 1);
          }
          if (longest[1] - longest[0] < (i + 1) - startIndex) {
            longest = new int[] { startIndex, i + 1};
          }
          lastSeen.put(c, i);
        }
        
        return str.substring(longest[0], longest[1]);

    }
}
