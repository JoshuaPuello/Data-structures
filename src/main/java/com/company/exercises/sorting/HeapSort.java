package com.company.exercises.sorting;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in an array of integers and returns a sorted version
 * of that array. Use the Heap Sort algorithm to sort the array.
 * ​
 * Sample input: [8, 5, 2, 9, 5, 6, 3]
 * Sample output: [2, 3, 5, 5, 6, 8, 9]
 */
public class HeapSort {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(heapSort((int[]) input[INPUT_INDEX])),
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
    public static int[] heapSort(int[] array) {
        buildMaxHeap(array);
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(0, endIdx, array);
            siftDown(0, endIdx - 1, array);
        }
        return array;
    }

    private static void buildMaxHeap(int[] array) {
        int firstParentIdx = (array.length - 2) / 2;
        for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
            siftDown(currentIdx, array.length - 1, array);
        }
    }

    private static void siftDown(int currentIdx, int endIdx, int[] heap) {
        int childOneIdx = currentIdx * 2 + 1;
        while (childOneIdx <= endIdx) {
            int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
            int idxToSwap;
            if (childTwoIdx != -1 && heap[childTwoIdx] > heap[childOneIdx]) {
                idxToSwap = childTwoIdx;
            } else {
                idxToSwap = childOneIdx;
            }
            if (heap[idxToSwap] > heap[currentIdx]) {
                swap(currentIdx, idxToSwap, heap);
                currentIdx = idxToSwap;
                childOneIdx = currentIdx * 2 + 1;
            } else {
                return;
            }
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
