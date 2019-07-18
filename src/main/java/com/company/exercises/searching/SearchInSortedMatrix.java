package com.company.exercises.searching;

import com.company.utils.Utils;

import java.util.Arrays;

/**
 * You are given a two-dimensional array (matrix) of distinct integers where each row is sorted and each
 * column is also sorted. The matrix does not necessarily have the same height and width. You are also
 * given a target number, and you must write a function that returns an array of the row and column indices
 * of the target number if it is contained in the matrix and [-1, -1] if it is not contained in the matrix.
 *
 * Sample input: [
 *      [1, 4, 7, 12, 15, 1000],
 *      [2, 5, 19, 31, 32, 1001],
 *      [3, 8, 24, 33, 35, 1002],
 *      [40, 41, 42, 44, 45, 1003],
 *      [99, 100, 103, 106, 128, 1004]
 * ], 44
 *
 * Sample output: [3, 3]
 */
public class SearchInSortedMatrix {

    public static final int TEST_NAME_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;

    private static final int[][] MATRIX = {
            {1, 4, 7, 12, 15, 1000},
            {2, 5, 19, 31, 32, 1001},
            {3, 8, 24, 33, 35, 1002},
            {40, 41, 42, 44, 45, 1003},
            {99, 100, 103, 106, 128, 1004}
    };

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(searchInSortedMatrixBinarySearch(MATRIX, (int) input[TARGET_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {

        Object[][] tests = {
                {"Test 1", 1, new int[]{0, 0}},
                {"Test 2", 2, new int[]{1, 0}},
                {"Test 3", 4, new int[]{0, 1}},
                {"Test 4", 15, new int[]{0, 4}},
                {"Test 5", 12, new int[]{0, 3}},
                {"Test 5", 32, new int[]{1, 4}},
                {"Test 5", 99, new int[]{4, 0}},
                {"Test 5", 100, new int[]{4, 1}},
                {"Test 5", -1, new int[]{-1, -1}},
                {"Test 5", 1004, new int[]{4, 5}},
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity O(1)
     */
    private static int[] searchInSortedMatrixIndexes(int[][] matrix, int target) {

        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
                col = matrix[row].length - 1;
            } else {
                return new int[]{row, col};
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Time complexity:
     *  BinarySearch -> {
     *      Best case: O(1)
     *      Avg case: O(log n)
     *  }
     */
    private static int[] searchInSortedMatrixBinarySearch(int[][] matrix, int target) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (target <= matrix[i][matrix[i].length - 1]) {
                    int searchIndex = Arrays.binarySearch(matrix[i], target);
                    if (searchIndex > -1) {
                        return new int[]{i, searchIndex};
                    }
                } else {
                    break;
                }
            }
        }

        return new int[]{-1, -1};

    }

}
