package com.company.datastructures;

import java.util.Arrays;

/**
 * - in-place algorithm
 * - Difficult to nail down the time complexity because it will depend on the gap.
 *   Worst case: O(n^2), but it can perform much better than that.
 * - Doesn't require as much shifting as insertion sort, so it usually performs better.
 * - Unstable algorithm
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] array = { 20, 18, -15, 1, 36, 57, -22 };

        for (int gap = array.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < array.length; i++) {

                int newElement = array[i];
                int j = i;

                while (j >= gap && array[j - gap] > newElement) {
                    array[i] = array[j - gap];
                    j -= gap;
                }

                array[j] = newElement;

            }

        }

        System.out.println(Arrays.toString(array));

    }

}
