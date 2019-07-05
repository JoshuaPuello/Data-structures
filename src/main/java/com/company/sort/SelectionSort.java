package com.company.sort;

import java.util.Arrays;

/**
 * - in-place algorithm
 * - O(n^2) time complexity - quadratic
 * - It will take 100 steps to sort 10 items
 * - Doesn't require as much swapping as bubble sort
 * - Unstable algorithm
 */
public class SelectionSort {

    public static void main(String[] args) {

        int[] array = { 20, 18, -15, 1, 36, 57, -22 };

        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            int largestIndex = 0;
            for (int i = 0; i <= lastUnsortedIndex; i++) {
                if (array[i] > array[largestIndex]) {
                    largestIndex = i;
                }
            }
            swap(array, largestIndex, lastUnsortedIndex);
        }

        System.out.println(Arrays.toString(array));

    }

    private static void swap(int[] array, int i, int j) {

        if (i == j) return;

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

    }

}
