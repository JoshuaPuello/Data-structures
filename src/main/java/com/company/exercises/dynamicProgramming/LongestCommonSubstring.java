package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

import static java.lang.Integer.max;

/**
 * Given two strings s1 and s2 return the length of the longest common
 * substring of characters between the two strings.
 * Sample input: S1: AXBCD S2: ABXCD
 * Sample output: 2 (CD)
 *
 * Dynamic programming
 *
 *     |  |''|A|B|X|C|D|
 *     |''|0 |0|0|0|0|0|
 *     |A |0 |1|0|0|0|0|
 *     |X |0 |0|0|1|0|0|
 *     |B |0 |0|1|0|0|0|
 *     |C |0 |0|0|0|1|0|
 *     |D |0 |0|0|0|0|2|
 */
public class LongestCommonSubstring {

    public static final int TEST_NAME_INDEX = 0;
    public static final int STRING_1 = 1;
    public static final int STRING_2 = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    longestCommonSubstring((String) input[STRING_1], (String) input[STRING_2]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        return new Object[][]{
                { "Test 1", "DBD", "CBD", 2 },
                { "Test 2", "ABCD", "ACXDBCF", 2 },
                { "Test 3", "ABCDAF", "ACDAF", 4 }
        };
    }

    /**
     * Using dynamic programming
     * Time complexity: O(nm)
     * Space complexity: O(nm)
     * n: s1.length() m: s1.length()
     */
    private static int longestCommonSubstring(String s1, String s2) {
        int[][] cache = new int[s1.length() + 1][s2.length() + 1];
        int maxValue = 0;
        for (int s1Row = 0; s1Row <= s1.length(); s1Row++) {
            for (int s2Col = 0; s2Col <= s2.length(); s2Col++) {
                if (s1Row == 0 || s2Col == 0) {
                    cache[s1Row][s2Col] = 0;
                } else if (s1.charAt(s1Row - 1) == s2.charAt(s2Col - 1)) {
                    cache[s1Row][s2Col] = cache[s1Row - 1][s2Col - 1] + 1;
                    maxValue = max(maxValue, cache[s1Row][s2Col]);
                } else {
                    cache[s1Row][s2Col] = 0;
                }
            }
        }

        return maxValue;
    }
}
