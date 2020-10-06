package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

import static java.lang.Integer.max;

/**
 * Given two strings s1 and s2 return the length of the longest common
 * subsequence of characters between the two strings.
 * Sample input: S1: ABCD S2: ACXDE
 * Sample output: 3 (ACD)
 *
 * Dynamic programming
 *
 *     |  |''|A|C|X|D|E|
 *     |''|0 |0|0|0|0|0|
 *     |A |0 |1|1|1|1|1|
 *     |B |0 |1|1|1|1|1|
 *     |C |0 |1|2|2|2|2|
 *     |D |0 |1|2|2|3|3|
 */
public class LongestCommonSubsequence {

    public static final int TEST_NAME_INDEX = 0;
    public static final int STRING_1 = 1;
    public static final int STRING_2 = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    longestCommonSubsequence((String) input[STRING_1], (String) input[STRING_2]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        return new Object[][]{
                { "Test 1", "DBC", "CBD", 1 },
                { "Test 2", "ABCD", "ACXDE", 3 },
                { "Test 3", "ABCDAF", "ACBCF", 4 }
        };
    }

    /**
     * Using dynamic programming
     * Time complexity: O(nm)
     * Space complexity: O(nm)
     * n: s1.length() m: s1.length()
     */
    private static int longestCommonSubsequence(String s1, String s2) {
        int[][] cache = new int[s1.length() + 1][s2.length() + 1];

        for (int s1Row = 0; s1Row <= s1.length(); s1Row++) {
            for (int s2Col = 0; s2Col <= s2.length(); s2Col++) {
                if (s1Row == 0 || s2Col == 0)
                    cache[s1Row][s2Col] = 0;
                else if (s1.charAt(s1Row - 1) == s2.charAt(s2Col - 1))
                    cache[s1Row][s2Col] = cache[s1Row - 1][s2Col - 1] + 1;
                else
                    cache[s1Row][s2Col] = max(cache[s1Row - 1][s2Col], cache[s1Row][s2Col - 1]);
            }
        }

        return cache[s1.length()][s2.length()];
    }
}
