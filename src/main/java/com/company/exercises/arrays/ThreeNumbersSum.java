package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static final int TEST_NAME_INDEX = 0;
    private static final int INPUT_INDEX = 1;
    private static final int SUM_INDEX = 2;
    private static final int EXPECTED_VALUE_INDEX = 3;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    threeNumbersSum((int[]) input[INPUT_INDEX], (int) input[SUM_INDEX]),
                    (List<Integer[]>) ((Stream) input[EXPECTED_VALUE_INDEX]).collect(Collectors.toList()));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1, 2, 3 }, 7, Stream.of() },
                { "Test 2", new int[] { 1, 2, 3 }, 6, Stream.of(
                        new Integer[][] { { 1, 2, 3 } }
                ) },
                { "Test 3", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 15 }, 32, Stream.of(
                        new Integer[][] { { 8, 9, 15 } }
                ) },
                { "Test 4", new int[] { 8, 10, -2, 49, 14 }, 57, Stream.of(
                        new Integer[][] { { -2, 10, 49 } }
                ) },
                { "Test 5", new int[] { 12, 3, 1, 2, -6, 5, -8, 6 }, 0, Stream.of(
                        new Integer[][] { { -8, 2, 6 }, { -8, 3, 5 }, { -6, 1, 5 } }
                ) },
                { "Test 6", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1 }, 0, Stream.of(
                        new Integer[][] { { -8, 3, 5 }, { -6, 1, 5 }, { -1, 0, 1 } }
                ) },
                { "Test 7", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1, 6 }, 0, Stream.of(
                        new Integer[][] { { -8, 2, 6 }, { -8, 3, 5 }, { -6, 0, 6 },
                                { -6, 1, 5 }, { -1, 0, 1 } }
                ) },
                { "Test 8", new int[] { 12, 3, 1, 2, -6, 5, 0, -8, -1, 6, -5 }, 0, Stream.of(
                        new Integer[][] { { -8, 2, 6 }, { -8, 3, 5 }, { -6, 0, 6 },
                                { -6, 1, 5 }, { -5, -1, 6 }, { -5, 0, 5 },
                                { -5, 2, 3 }, { -1, 0, 1 } }
                ) }
        };
        return tests;
    }

    /**
     * Time complexity O(n^2)
     * Space complexity O(n)
     */
    private static List<Integer[]> threeNumbersSum(int[] array, int targetSum) {

        Arrays.sort(array);
        ArrayList<Integer[]> triplets = new ArrayList<>();

        for(int i = 0; i < array.length; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    triplets.add(new Integer[] { array[i], array[left], array[right] });
                    right--;
                    left++;
                } else if (currentSum > targetSum) {
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                }
            }
        }

        return triplets;
    }
}
