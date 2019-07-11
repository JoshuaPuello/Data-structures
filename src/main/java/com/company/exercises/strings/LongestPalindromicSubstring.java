package com.company.exercises.strings;

import com.company.utils.Utils;

/**
 * Write a function that, given a string, returns its longest palindromic substring.
 * A palindrome is defined as a string that is written the same forward and backward.
 * Assume that there will only be one longest palindromic substring.
 *
 * Sample input: "abaxyzzyxf"
 * Sample output: "xyzzyx"
 */
public class LongestPalindromicSubstring {

    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    longestPalindromicSubstring(String.valueOf(input[INPUT_INDEX])),
                    String.valueOf(input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests ={
                { "Test 1", "a", "a" },
                { "Test 2", "it's highnoon", "noon" },
                { "Test 3", "noon high it is", "noon" },
                { "Test 4", "abccbait's highnoon", "abccba" },
                { "Test 5", "abaxyzzyxf", "xyzzyx" },
                { "Test 6", "abcdefgfedcbazzzzzzzzzzzzzzzzzzzz", "zzzzzzzzzzzzzzzzzzzz" },
                { "Test 7", "abcdefgfedcba", "abcdefgfedcba" },
                { "Test 8", "zzzzzzz2345abbbba5432zzbbababa", "zz2345abbbba5432zz" },
                { "Test 9", "z234a5abbba54a32z", "5abbba5" }
        };
        return tests;
    }

    /**
     * Time complexity O(n^2)
     * Space complexity 0(1)
     */
    public static String longestPalindromicSubstring(String str) {

        int[] currentLongest = {0, 1};

        for (int i = 1; i < str.length(); i++) {
            int[] odd = getLongestPalindromeFrom(str, i - 1, i + 1);
            int[] even = getLongestPalindromeFrom(str, i - 1, i);
            int[] longest = (odd[1] - odd[0] > even[1] - even[0]) ? odd : even;
            currentLongest = (longest[1] - longest[0] > currentLongest[1] - currentLongest[0]) ?
                    longest : currentLongest;
        }

        return str.substring(currentLongest[0], currentLongest[1]);

    }

    public static int[] getLongestPalindromeFrom(String str, int leftIdx, int rightIdx) {

        while (leftIdx >= 0 && rightIdx < str.length()) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) break;
            leftIdx--;
            rightIdx++;
        }

        return new int[] { leftIdx + 1, rightIdx };

    }
}
