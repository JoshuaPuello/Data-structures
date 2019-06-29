package com.company.datastructures;

/**
 * - in-place algorithm
 * - O(n^2) time complexity - quadratic
 * - It will take 100 steps to sort 10 items
 * - Stable algorithm
 */
public class InsertionSort {

    public static void main(String[] args) {

        int[] array = { 20, 18, -15, 1, 36, 57, -22 };

        for (int firstUnsortedIndex = 1; firstUnsortedIndex < array.length; firstUnsortedIndex++) {
            int newElement = array[firstUnsortedIndex];
            int i;
            for (i = firstUnsortedIndex; i > 0 && array[i - 1] > newElement; i--) {
                array[i] = array[i - 1];
            }
            array[i] = newElement;
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
