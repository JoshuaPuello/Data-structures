package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Given a non-empty string, write a function that returns the minimum number of cuts
 * needed to perform on the string such that each remaining substring is a palindrome.
 * A palindrome is defined as a string that is written the same forward as backward.
 * Note that single-character strings are palindromes.
 * ​
 * Sample input: "noonabbad"
 * Sample output: 2 ("noon | abba | d")
 */
public class PalindromePartitioning {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    palindromePartitioningMinCuts(String.valueOf(input[INPUT_INDEX])),
                    (int) (input[EXPECTED_VALUE_INDEX]));
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", "a", 0 },
                { "Test 2", "abba", 0 },
                { "Test 3", "abbba", 0 },
                { "Test 4", "abb", 1 },
                { "Test 5", "noonabbad", 2 },
                { "Test 6", "ababbbabbababa", 3 },
                { "Test 7", "abcdefghijklmonpqrstuvwxyz", 25 },
                { "Test 8", "abcdefghijklmracecaronpqrstuvwxyz", 26 },
        };
        return tests;
    }

    /**
     * Time complexity O(n^3)
     * Space complexity O(n^2)
     */
    private static int palindromePartitioningMinCuts(String str) {

        boolean[][] palindromes = new boolean[str.length()][str.length()];

        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                palindromes[i][j] = isPalindrome(str.substring(i, j + 1));
            }
        }

        return fillArray(str, palindromes);
    }

    private static boolean isPalindrome(String str) {

        int leftIdx = 0;
        int rightIdx = str.length() - 1;

        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                return false;
            }
            leftIdx++;
            rightIdx--;
        }

        return true;
    }

    // *********************

    /**
     * Time complexity O(n^2)
     * Space complexity O(n^2)
     */
    private static int palindromePartitioningMinCutsAlternative(String str) {

        boolean[][] palindromes = new boolean[str.length()][str.length()];

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                palindromes[i][j] = i == j;
            }
        }

        for (int length = 2; length < str.length() + 1; length++) {
            for (int i = 0; i < str.length() - length + 1; i++) {
                int j = i + length - 1;
                boolean b = str.charAt(i) == str.charAt(j);
                if (length == 2) {
                    palindromes[i][j] = b;
                } else {
                    palindromes[i][j] = (b && palindromes[i + 1][j - 1]);
                }
            }
        }

        return fillArray(str, palindromes);
    }

    private static int fillArray(String str, boolean[][] palindromes) {

        int[] cuts = new int[str.length()];
        Arrays.fill(cuts, Integer.MAX_VALUE);

        for (int i = 0; i < str.length(); i++) {
            if (palindromes[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = cuts[i - 1] + 1;
                for (int j = 1; j < i; j++) {
                    if (palindromes[j][i] && cuts[j - 1] + 1 < cuts[i]) {
                        cuts[i] = cuts[j - 1] + 1;
                    }
                }
            }
        }

        return cuts[str.length() - 1];
    }

}
