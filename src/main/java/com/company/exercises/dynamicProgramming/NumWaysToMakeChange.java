package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

/**
 * Given an array of positive integers representing coin denominations and a
 * single non-negative integer representing a target amount of money, implement
 * a function that returns the number of ways to make change for that target
 * amount using the given coin denominations. Note that an unlimited amount of
 * coins is at your disposal.
 * ​
 * Sample input: 6, [1, 5]
 * Sample output: 2 (1x1 + 1x5 and 6x1)
 */
public class NumWaysToMakeChange {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    numberOfWaysToMakeChange((int) input[TARGET_INDEX], (int[]) input[INPUT_INDEX]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 2, 3, 4, 7 }, 0, 1 },
                { "Test 2", new int[] { 5 }, 9, 0 },
                { "Test 3", new int[] { 2, 4 }, 7, 0 },
                { "Test 4", new int[] { 1, 5, 10, 25 }, 4, 1 },
                { "Test 5", new int[] { 1, 5, 10, 25 }, 5, 2 },
        };
        return tests;
    }

    /**
     * Time complexity: O(nd) | n: target amount ; d: number of coin denominations
     * Space complexity: O(n)
     */
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {

        int[] ways = new int[n + 1];
        ways[0] = 1;

        for (int denom : denoms) {
            for (int amount = 1; amount < n + 1; amount++) {
                if (denom <= amount) {
                    ways[amount] += ways[amount - denom];
                }
            }
        }

        return ways[n];
    }
}
