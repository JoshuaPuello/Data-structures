package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * Write a function that takes in two non-empty arrays of integers. The function should
 * find the pair of numbers (one from the first array, one from the second array) whose
 * absolute difference is closest to zero. The function should return an array containing
 * these two numbers, with the number from the first array in the first position. Assume
 * that there will only be one pair of numbers with the smallest difference.
 *
 * Sample input: [-1, 5, 10, 20, 28, 3], [26, 134, 135, 15, 17]
 * Sample output: [28, 26]
 */
public class SmallestDifference {

    public static final int TEST_NAME_INDEX = 0;
    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(smallestDifference((int[][]) input[INPUT_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[][] { { -1, 5, 10, 20, 3 },
                        { 26, 134, 135, 15, 17 } }, new int[] { 20, 17 } },
                { "Test 2", new int[][] { { -1, 5, 10, 20, 28, 3 },
                        { 26, 134, 135, 15, 17 } }, new int[] { 28, 26 } },
                { "Test 3", new int[][] { { 10, 0, 20, 25 },
                        { 1005, 1006, 1014, 1032, 1031 } }, new int[] { 25, 1005 } },
                { "Test 4", new int[][] { { 10, 0, 20, 25, 2200 },
                        { 1005, 1006, 1014, 1032, 1031 } }, new int[] { 25, 1005 } },
                { "Test 5", new int[][] { { 10, 0, 20, 25, 2000 },
                        { 1005, 1006, 1014, 1032, 1031 } }, new int[] { 2000, 1032 } },
                { "Test 6", new int[][] { { 240, 124, 86, 111, 2, 84, 954, 27, 89 },
                        { 1, 3, 954, 19, 8 } }, new int[] { 954, 954 } },
                { "Test 7", new int[][] { { 0, 20 },
                        { 21, -2 } }, new int[] { 20, 21 } },
                { "Test 8", new int[][] { { 10, 1000 },
                        { -1441, -124, -25, 1014, 1500, 660, 410, 245, 530 } }, new int[] { 1000, 1014 } },
                { "Test 9", new int[][] { { 10, 1000, 9124, 2142, 59, 24, 596, 591, 124, -123 },
                        { -1441, -124, -25, 1014, 1500, 660, 410, 245, 530 } }, new int[] { -123, -124 } },
                { "Test 10", new int[][] { { 10, 1000, 9124, 2142, 59, 24, 596, 591, 124, -123, 530 },
                        { -1441, -124, -25, 1014, 1500, 660, 410, 245, 530 } }, new int[] { 530, 530 } },
        };
        return tests;
    }

    /**
     * Time complexity O(n log(n) + m log(m))
     * Space complexity O(1)
     */
    private static int[] smallestDifference(int[][] twoDArray) {

        Arrays.sort(twoDArray[0]);
        Arrays.sort(twoDArray[1]);

        int left = 0;
        int right = 0;
        int smallestDiff = Integer.MAX_VALUE;
        int currentDiff = 0;

        int[] smallestPair = new int[2];

        while (left < twoDArray[0].length && right < twoDArray[1].length) {
            int firstNum = twoDArray[0][left];
            int secondNum = twoDArray[1][right];
            if (firstNum < secondNum) {
                currentDiff = secondNum - firstNum;
                left++;
            } else if (firstNum > secondNum) {
                currentDiff = firstNum - secondNum;
                right++;
            } else {
                return new int[] { firstNum, secondNum};
            }
            if (currentDiff < smallestDiff) {
                smallestDiff = currentDiff;
                smallestPair = new int[] { firstNum, secondNum };
            }
        }

        return smallestPair;
    }

}
