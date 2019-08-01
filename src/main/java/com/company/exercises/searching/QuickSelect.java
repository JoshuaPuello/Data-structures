package com.company.exercises.searching;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in an array of distinct integers as well as an
 * integer k and returns the kth smallest number in that array in linear time,
 * on average. The array could be sorted, but isn't necessarily so.
 * ​
 * Sample input: [8, 5, 2, 9, 7, 6, 3], 3
 * Sample output: 5
 */
public class QuickSelect {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    quickselect((int[]) input[INPUT_INDEX], (int) input[TARGET_INDEX]),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                {"Test 1", new int[] {1}, 1, 1},
                {"Test 2", new int[] {43, 24, 37}, 1, 24},
                {"Test 3", new int[] {43, 24, 37}, 2, 37},
                {"Test 4", new int[] {8, 5, 2, 9, 7, 6, 3}, 3, 5},
                {"Test 5", new int[] {8, 3, 2, 5, 1, 7, 4, 6}, 1, 1},
        };
        return tests;
    }

    /**
     * Time complexity: Best case: O(n) | Worst case: O(n) | Average case: O(n^2)
     * Space complexity: O(1)
     */
    private static int quickselect(int[] array, int k) {
        int position = k - 1;
        return quickselect(array, 0, array.length - 1, position);
    }

    private static int quickselect(int[] array, int startIdx, int endIdx, int position) {

        while (true) {

            if (startIdx > endIdx) {
                throw new RuntimeException("Your Algorithm should never arrive here!");
            }

            int pivotIdx = startIdx;
            int leftIdx = startIdx + 1;
            int rightIdx = endIdx;

            while (leftIdx <= rightIdx) {
                if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]) {
                    swap(leftIdx, rightIdx, array);
                }
                if (array[leftIdx] <= array[pivotIdx]) {
                    leftIdx++;
                }
                if (array[rightIdx] >= array[pivotIdx]) {
                    rightIdx--;
                }
            }

            swap(pivotIdx, rightIdx, array);

            if (rightIdx == position) {
                return array[rightIdx];
            } else if (rightIdx < position) {
                startIdx = rightIdx + 1;
            } else {
                endIdx = rightIdx - 1;
            }
        }

    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
