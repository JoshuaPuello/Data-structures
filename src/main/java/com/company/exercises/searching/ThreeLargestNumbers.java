package com.company.exercises.searching;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Write a function that takes in an array of integers and returns a sorted array of the three largest
 * integers in the input array. Note that the function should return duplicate integers if necessary;
 * for example, it should return [10, 10, 12] for an input array of [10, 5, 9, 10, 12].
 *
 * Sample input: [141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7]
 * Sample output: [18, 141, 541]
 */
public class ThreeLargestNumbers {

    public static final int EXPECTED_VALUE_INDEX = 2;
    public static final int INPUT_INDEX = 1;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(updateLargestNumbers((int[]) input[INPUT_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 55, 7, 8 }, new int[] { 7, 8, 55 } },
                { "Test 2", new int[] { 55, 43, 11, 3, -3, 10 }, new int[] { 11, 43, 55 } },
                { "Test 3", new int[] { 7, 8, 3, 11, 43, 55 }, new int[] { 11, 43, 55 } },
                { "Test 4", new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 }, new int[] { 7, 7, 7 } },
                { "Test 5", new int[] { -1, -2, -3, -7, -17, -27, -18, -541, -8, -7, 7 }, new int[] { -2, -1, 7 } }
        };
        return tests;
    }

    /**
     * Time complexity 0(n)
     * Space complexity O(1)
     */
    private static int[] updateLargestNumbers(int[] numbers) {
        int[] largestNums = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };

        for (int number : numbers) {
            if (number > largestNums[2]) insertLargestNum(largestNums, number, 2);
            else if (number > largestNums[1]) insertLargestNum(largestNums, number, 1);
            else if (number > largestNums[0]) insertLargestNum(largestNums, number, 0);
        }

        return largestNums;
    }

    private static void insertLargestNum(int[] largestNums, int number, int index) {
        int current = largestNums[index];
        largestNums[index] = number;
        switch (index) {
            case 2: {
                largestNums[index - 2] = largestNums[index - 1];
                largestNums[index - 1] = current;
                break;
            }
            case 1: {
                largestNums[index - 1] = current;
                break;
            }
        }
    }

}
