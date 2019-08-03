package com.company.exercises.dynamicProgramming;

import com.company.utils.Utils;

/**
 * Write a function that takes in an array of positive integers and returns an
 * integer representing the maximum sum of non-adjacent elements in the array.
 * If a sum cannot be generated, the function should return 0.
 * ​
 * Sample input: [75, 105, 120, 75, 90, 135]
 * Sample output: 330 (75, 120, 135)
 */
public class MaximumSubsetSum {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            int[] array = (int[]) input[INPUT_INDEX];
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    maxSubsetSumNoAdjacent(array),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 10, 5, 20, 25, 15, 5, 5, 15 }, 60 },
                { "Test 2", new int[] { 10, 5, 20, 25, 15, 5, 5, 15, 3, 15, 5, 5, 15 }, 90 },
                { "Test 2", new int[] { 125, 21, 250, 120, 150, 300 }, 675 },
                { "Test 4", new int[] { 30, 25, 50, 55, 100 }, 180 },
                { "Test 5", new int[] { 7, 10, 12, 7, 9, 14, 15, 16, 25, 20, 4 }, 72 },
                { "Test 6", new int[] { 30, 25, 50, 55, 100, 120 }, 205 }
        };
        return tests;
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static int maxSubsetSumNoAdjacent(int[] array) {

        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }

        int[] maxSums = array.clone();
        maxSums[1] = Math.max(array[0], array[1]);

        for (int i = 2; i < array.length; i++) {
            maxSums[i] = Math.max(maxSums[i - 1], maxSums[i - 2] + array[i]);
        }

        return maxSums[array.length - 1];
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static int maxSubsetSumNoAdjacentPointers(int[] array) {

        if (array.length == 0) {
            return 0;
        } else if (array.length == 1) {
            return array[0];
        }

        int second = array[0];
        int first = Math.max(array[0], array[1]);

        for (int i = 2; i < array.length; i++) {
            int current = Math.max(first, second + array[i]);
            second = first;
            first = current;
        }

        return first;
    }
}
