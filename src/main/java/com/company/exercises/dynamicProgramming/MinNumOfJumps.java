package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * You are given a non-empty array of integers. Each element represents the maximum
 * number of steps you can take forward. For example, if the element at index 1 is 3,
 * you can go from index 1 to index 2, 3, or 4. Write a function that returns the
 * minimum number of jumps needed to reach the final index. Note that jumping from
 * index i to index i + x always constitutes 1 jump, no matter how large x is.
 * ​
 * Sample input: [3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3]
 * Sample output: 4 (3 --> 4 or 2 --> 2 or 3 --> 7 --> 3)
 */
public class MinNumOfJumps {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            int[] array = (int[]) input[INPUT_INDEX];
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    minNumberOfJumps(array),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1 }, 0 },
                { "Test 2", new int[] { 1, 1 }, 1 },
                { "Test 3", new int[] { 3, 1 }, 1 },
                { "Test 4", new int[] { 1, 1, 1 }, 2 },
                { "Test 5", new int[] { 2, 1, 1 }, 1 },
        };
        return tests;
    }

    /**
     * Time complexity O(n^2)
     * Space complexity O(n)
     */
    public static int minNumberOfJumps(int[] array) {

        int[] jumps = new int[array.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] >= i -j) {
                    jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
                }
            }
        }

        return jumps[jumps.length - 1];
    }
}
