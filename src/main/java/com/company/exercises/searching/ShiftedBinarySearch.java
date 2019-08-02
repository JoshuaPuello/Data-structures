package com.company.exercises.searching;

import com.company.utils.Utils;

/**
 * Write a function that takes in a sorted array of integers as well as a target
 * integer. The caveat is that the numbers in the array have been shifted by some
 * amount; in other words, they have been moved to the left or the right by one or
 * more positions. For example, [1, 2, 3, 4] might become [3, 4, 1, 2]. The function
 * should use a variation of the Binary Search algorithm to find if the target number
 * is contained in the array and should return its index if it is, otherwise -1.
 * ​
 * Sample input: [45, 61, 71, 72, 73, 0, 1, 21, 33, 45], 33
 * Sample output: 8
 */
public class ShiftedBinarySearch {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            int[] array = (int[]) input[INPUT_INDEX];
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    shiftedBinarySearch(array, (int) input[TARGET_INDEX], 0, array.length - 1),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                {"Test 1", new int[] {5, 23, 111, 1}, 111, 2},
                {"Test 2", new int[] {111, 1, 5, 23}, 5, 2},
                {"Test 3", new int[] {23, 111, 1, 5}, 35, -1},
                {"Test 4", new int[] {45, 61, 71, 72, 73, 0, 1, 21, 33, 45}, 33, 8},
                {"Test 5", new int[] {72, 73, 0, 1, 21, 33, 45, 45, 61, 71}, 72, 0},
                {"Test 6", new int[] {73, 0, 1, 21, 33, 45, 45, 61, 71, 72}, 70, -1},
        };
        return tests;
    }

    /**
     * Time complexity: O(log(n))
     * Space complexity: O(log(n))
     */
    private static int shiftedBinarySearch(int[] array, int target) {
        return shiftedBinarySearch(array, target, 0, array.length - 1);
    }

    private static int shiftedBinarySearch(int[] array, int target, int left, int right) {

        if (left > right) {
            return -1;
        }

        int middle = (left + right) / 2;
        int potentialMatch = array[middle];
        int leftNum = array[left];
        int rightNum = array[right];

        if (target == potentialMatch) {
            return middle;
        } else if (leftNum <= potentialMatch) {
            if (target < potentialMatch && target >= leftNum) {
                return shiftedBinarySearch(array, target, left, middle - 1);
            } else {
                return shiftedBinarySearch(array, target, middle + 1, right);
            }
        } else {
            if (target > potentialMatch && target <= rightNum) {
                return shiftedBinarySearch(array, target, middle + 1, right);
            } else {
                return shiftedBinarySearch(array, target, left, middle - 1);
            }
        }

    }

}
