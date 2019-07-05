package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum.
 * If any two numbers in the input array sum up to the target sum, the function should return them in an array,
 * in sorted order. If no two numbers sum up to the target sum, the function should return an empty array. Assume
 * that there will be at most one pair of numbers summing up to the target sum.
 *
 * Sample input: [3, 5, -4, 8, 11, 1, -1, 6], 10
 * Sample output: [-1, 11]
 *
 */
public class TwoNumbersSum {

    private static final int ARRAY_INDEX = 1;
    private static final int EXPECTED_INDEX = 3;
    private static final int SUM_INDEX = 2;
    private static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString((int[]) input[EXPECTED_INDEX]),
                    Arrays.toString(twoNumbersSum((int[]) input[ARRAY_INDEX], (int) input[SUM_INDEX])));
        }

    }

    private static List<Object[]> getInputs() {
        Object[] test1 = new Object[] { "Test 1", new int[] { 3, 5, -4, 8, 11, 1, -1, 6 }, 10, new int[] {-1, 11}};
        Object[] test2 = new Object[] { "Test 2", new int[] { 3, 5, -4, 8, 11, 1, -1, 6 }, 10, new int[] {-1, 11}};
        return Arrays.asList(test1, test2);
    }

    /**
     * Since stream is lazy loading, time complexity for filter is O(n) in the worst case
     * We can use Map instead of /int[] complement/
     * Search operation in Map is 0(1) in most of the cases
     */
    private static int[] twoNumbersSum(int[] array, int sum) {

        // HashMap<Integer, Integer> complement = new HashMap<>();
        int[] complement = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int complementInt = sum - current;
            // if (complement.containsKey(current)) {
            if (IntStream.of(complement).anyMatch(x -> x == current)) {
                int min = Math.min(current, complementInt);
                int max = (min == current) ? complementInt : current;
                return new int[] { min, max };
            }
            // complement.put(complementInt, complementInt)
            complement[i] = complementInt;
        }

        return new int[] {};
    }

}
