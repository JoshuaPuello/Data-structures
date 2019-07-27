package com.company.exercises.sorting;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Merge Sort algorithm to sort the array.
 *
 * Sample input: [8, 5, 2, 9, 5, 6, 3]
 * Sample output: [2, 3, 5, 5, 6, 8, 9]
 */
public class MergeSort {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(mergeSort((int[]) input[INPUT_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] {1}, new int[] {1} },
                { "Test 2", new int[] {1, 2}, new int[] {1, 2} },
                { "Test 3", new int[] {2, 1}, new int[] {1, 2} },
                { "Test 4", new int[] {1, 3, 2}, new int[] {1, 2, 3} },
                { "Test 5", new int[] {5, -2, 2, -8, 3, -10, -6, -1, 2, -2, 9, 1, 1},
                        new int[] {-10, -8, -6, -2, -2, -1, 1, 1, 2, 2, 3, 5, 9} },
        };
        return tests;
    }

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int middleIdx = array.length / 2;
        int[] leftHalf = Arrays.copyOfRange(array, 0, middleIdx);
        int[] rightHalf = Arrays.copyOfRange(array, middleIdx, array.length);
        return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j]) {
                sortedArray[k++] = leftHalf[i++];
            } else {
                sortedArray[k++] = rightHalf[j++];
            }
        }
        while (i < leftHalf.length) {
            sortedArray[k++] = leftHalf[i++];
        }
        while (j < rightHalf.length) {
            sortedArray[k++] = rightHalf[j++];
        }
        return sortedArray;
    }
}
