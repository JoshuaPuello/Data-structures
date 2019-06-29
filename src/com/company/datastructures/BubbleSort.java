package com.company.datastructures;

/**
 * - in-place algorithm
 * - O(n^2) time complexity: quadratic
 * - It will take 100 steps to sort 10 items
 * - Algorithm degrades quickly
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] array = { 20, 18, -15, 1, 36, 57, -22 };

        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }

    private static void swap(int[] array, int i, int j) {

        if (i == j) return;

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

    }
}
