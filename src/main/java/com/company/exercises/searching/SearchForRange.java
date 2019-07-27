package com.company.exercises.searching;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in a sorted array of integers as well as a target
 * integer. The function should use a variation of the Binary Search algorithm
 * to find a range of indices in between which the target number is contained in
 * the array and should return this range in the form of an array. The first
 * number in the output array should represent the first index at which the target
 * number is located while the second number should represent the last index at
 * which the target number is located. The function should return [-1, -1] if the
 * number is not contained in the array.
 *
 * Sample input: [0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73], 45
 * Sample output: [4, 9]
 */
public class SearchForRange {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 3;
    public static final int TARGET_INDEX = 2;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(searchForRange((int[]) input[INPUT_INDEX], (int) input[TARGET_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] {5, 7, 7, 8, 8, 10}, 5, new int[] {0, 0} },
                { "Test 2", new int[] {5, 7, 7, 8, 8, 10}, 7, new int[] {1, 2} },
                { "Test 3", new int[] {5, 7, 7, 8, 8, 10}, 8, new int[] {3, 4} },
                { "Test 4", new int[] {5, 7, 7, 8, 8, 10}, 10, new int[] {5, 5} },
                { "Test 5", new int[] {0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 45, 45, 45}, 45, new int[] {4, 12} },
        };
        return tests;
    }

    public static int[] searchForRange(int[] array, int target) {
        int[] finalRange = {-1, -1};
        alteredBinarySearch(array, target, 0, array.length - 1, finalRange, true);
        alteredBinarySearch(array, target, 0, array.length - 1, finalRange, false);
        return finalRange;
    }

    public static void alteredBinarySearch(int[] array, int target, int left, int right, int[] finalRange, boolean goLeft) {
        if (left > right) {
            return;
        }
        int mid = (left + right) / 2;
        if (array[mid] < target) {
            alteredBinarySearch(array, target, mid + 1, right, finalRange, goLeft);
        } else if (array[mid] > target){
            alteredBinarySearch(array, target, left, mid - 1, finalRange, goLeft);
        } else {
            if (goLeft) {
                if (mid == 0 || array[mid - 1] != target) {
                    finalRange[0] = mid;
                } else {
                    alteredBinarySearch(array, target, left, mid - 1, finalRange, goLeft);
                }
            } else {
                if (mid == array.length - 1 || array[mid + 1] != target) {
                    finalRange[1] = mid;
                } else {
                    alteredBinarySearch(array, target, mid + 1, right, finalRange, goLeft);
                }
            }
        }
    }
}
