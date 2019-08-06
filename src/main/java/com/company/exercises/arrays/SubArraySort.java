package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in an array of integers of length at least 2. The function should return
 * an array of the starting and ending indices of the smallest subarray in the input array that needs
 * to be sorted in place in order for the entire input array to be sorted. If the input array is already
 * sorted, the function should return [-1, -1].
 *
 * Sample input: [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
 * Sample output: [3, 9]
 */
public class SubArraySort {

    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(subArraySort((int[]) (input[INPUT_INDEX]))),
                    Arrays.toString((int[])(input[EXPECTED_VALUE_INDEX])));
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1, 2 }, new int[] { -1, -1 } },
                { "Test 2", new int[] { 2, 1 }, new int[] { 0, 1 } },
                { "Test 3", new int[] { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 }, new int[] { 3, 9 } },
                { "Test 4", new int[] { 1, 2, 4, 7, 10, 11, 7, 12, 7, 7, 16, 18, 19 }, new int[] { 4, 9 } },
                { "Test 5", new int[] { 1, 2, 4, 7, 10, 11, 7, 12, 13, 14, 16, 18, 19 }, new int[] { 4, 6 } },
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity 0(1)
     */
    private static int[] subArraySort(int[] array) {

        int minOutOfOrder = Integer.MAX_VALUE;
        int maxOutOfOrder = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            if (isOutOfOrder(i, num, array)) {
                minOutOfOrder = Math.min(minOutOfOrder, num);
                maxOutOfOrder = Math.max(maxOutOfOrder, num);
            }
        }

        if (minOutOfOrder == Integer.MAX_VALUE) {
            return new int[] {-1, -1};
        }

        int subArrayLeftIdx = 0;

        while (minOutOfOrder >= array[subArrayLeftIdx]) {
            subArrayLeftIdx++;
        }

        int subArrayRightIdx = array.length - 1;

        while (maxOutOfOrder <= array[subArrayRightIdx]) {
            subArrayRightIdx--;
        }

        return new int[] {subArrayLeftIdx, subArrayRightIdx};
    }

    private static boolean isOutOfOrder(int i, int num, int[] array) {

        if (i == 0) return num > array[i + 1];
        if (i == array.length - 1) return num < array[i - 1];

        return num > array[i + 1] || num < array[i - 1];

    }

}
