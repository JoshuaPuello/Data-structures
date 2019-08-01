package com.company.exercises.sorting;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Quick Sort algorithm to sort the array.
 * ​
 * Sample input: [8, 5, 2, 9, 5, 6, 3]
 * Sample output: [2, 3, 5, 5, 6, 8, 9]
 */
public class QuickSort {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(quickSort((int[]) input[INPUT_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                {"Test 1", new int[]{1}, new int[]{1}},
                {"Test 2", new int[]{1, 2}, new int[]{1, 2}},
                {"Test 3", new int[]{2, 1}, new int[]{1, 2}},
                {"Test 4", new int[]{8, 5, 2, 9, 5, 6, 3}, new int[]{2, 3, 5, 5, 6, 8, 9}},
                {"Test 5", new int[]{5, -2, 2, -8, 3, -10, -6, -1, 2, -2, 9, 1, 1},
                        new int[]{-10, -8, -6, -2, -2, -1, 1, 1, 2, 2, 3, 5, 9}},
        };
        return tests;
    }


    /**
     * Time complexity: Best case: O(n log(n)) | Worst case: O(n log(n)) | Average case: O(n^2)
     * Space complexity: O(log(n))
     */
    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] array, int startIdx, int endIdx) {

        if (startIdx >= endIdx) {
            return;
        }

        int pivotIdx = startIdx;
        int leftIdx = startIdx + 1;
        int rightIdx = endIdx;

        while (rightIdx >= leftIdx) {
            if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]) {
                swap(leftIdx, rightIdx, array);
            }
            if (array[leftIdx] <= array[pivotIdx]) {
                leftIdx += 1;
            }
            if (array[rightIdx] >= array[pivotIdx]) {
                rightIdx -= 1;
            }
        }

        swap(pivotIdx, rightIdx, array);

        boolean leftSubarrayIsSmaller = rightIdx - 1 - startIdx < endIdx - (rightIdx + 1);

        if (leftSubarrayIsSmaller) {
            quickSort(array, startIdx, rightIdx - 1);
            quickSort(array, rightIdx + 1, endIdx);
        } else {
            quickSort(array, rightIdx + 1, endIdx);
            quickSort(array, startIdx, rightIdx - 1);
        }

    }

    private static void swap(int i, int j, int[] array) {
        int temp  = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
