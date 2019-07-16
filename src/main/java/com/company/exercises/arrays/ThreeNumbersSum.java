package com.company.exercises.arrays;

/**
 * Write a function that takes in a non-empty array of distinct integers and an integer
 * representing a target sum. The function should find all triplets in the array that
 * sum up to the target sum and return a two-dimensional array of all these triplets.
 * The numbers in each triplet should be ordered in ascending order, and the triplets
 * themselves should be ordered in ascending order with respect to the numbers they
 * hold. If no three numbers sum up to the target sum, the function should return an
 * empty array.
 *
 * Sample input: [12, 3, 1, 2, -6, 5, -8, 6], 0
 * Sample output: [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 */
public class ThreeNumbersSum {

    public static void main(String[] args) {

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1, 2, 3 }, 7, new int[][] {{  }} },
                { "Test 2", new int[] { 1, 2, 3 }, 6, new int[][] {
                        { 1, 2, 3 }
                } },
                { "Test 3", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 15 }, 32, new int[][] {
                        { 8, 9, 15 }
                } },
                { "Test 4", new int[] { 8, 10, -2, 49, 14 }, 57, new int[][] {
                        { -2, 10, 49 }
                } },
                { "Test 5", new int[] { 12, 3, 1, 2, -6, 5, -8, 6 }, 0, new int[][] {
                        { -8, 2, 6 }, { -8, 3, 5 }, { -6, 1, 5 }
                }},
                { "Test 6", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1 }, 0, new int[][] {
                        { -8, 3, 5 }, { -6, 1, 5 }, { -1, 0, 1 }
                } },
                { "Test 7", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1, 6 }, 0, new int[][] {
                        { -8, 2, 6 }, { -8, 3, 5 }, { -6, 0, 6 },
                        { -6, 1, 5 }, { -1, 0, 1 }
                } },
                { "Test 8", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1, 6, -5 }, 0, new int[][] {
                        { -8, 2, 6 }, { -8, 3, 5 }, { -6, 0, 6 },
                        { -6, 1, 5 }, { -5, -1, 6 }, { -5, 0, 5 },
                        { -5, 2, 3 }, { -1, 0, 1 },
                } }
        };
        return tests;
    }
}
