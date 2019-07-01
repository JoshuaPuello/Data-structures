package com.company.datastructures;

import java.util.Arrays;

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
