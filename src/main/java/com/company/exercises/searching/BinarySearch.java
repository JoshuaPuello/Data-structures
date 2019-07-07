package com.company.exercises.searching;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Write a function that takes in a sorted array of integers as well as a target integer. The function should
 * use the Binary Search algorithm to find if the target number is contained in the array and should return its
 * index if it is, otherwise -1.
 *
 * Sample input: [0, 1, 21, 33, 45, 45, 61, 71, 72, 73], 33
 * Sample output: 3
 *
 */
public class BinarySearch {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            int[] array = (int[]) input[INPUT_INDEX];
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    binarySearchRecursion(array, (int) input[TARGET_INDEX], 0, array.length - 1),
                    (int) input[EXPECTED_VALUE_INDEX]);
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 0, 1, 21, 33, 45, 45, 61, 71, 72, 73 }, 33, 3 },
                { "Test 2", new int[] { 1, 5, 23, 111 }, 111, 3 },
                { "Test 2", new int[] { 1, 5, 23, 111 }, 5, 1 },
                { "Test 4", new int[] { 0, 1, 21, 33, 45, 45, 61, 71, 72, 73, 355 }, 355, 10 },
                { "Test 4", new int[] { 0, 1, 21, 33, 45, 45, 61, 71, 72, 73, 355 }, 354, -1 }
        };
        return tests;
    }

    /**
     * Time complexity O(log(n))
     * Space complexity O(log(n))
     */
    private static int binarySearchRecursion(int[] array, int target, int left, int right) {
        if (left > right) return -1;
        int middle = (left + right) / 2;
        int current = array[middle];
        if (current > target) return binarySearchRecursion(array, target, left, middle - 1);
        else if (current < target) return  binarySearchRecursion(array, target, middle + 1, right);
        else return middle;
    }

    /**
     * Time complexity O(log(n))
     * Space complexity O(1)
     */
    private static int binarySearchIterative(int[] array, int target, int left, int right) {
        while (left <= right){
            int middle = (left + right) / 2;
            int current = array[middle];
            if (current > target) right = middle - 1;
            else if (current < target) left = middle + 1;
            else return middle;
        }
        return -1;
    }
}


