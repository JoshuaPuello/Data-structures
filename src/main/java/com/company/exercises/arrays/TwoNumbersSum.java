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

    private static final int INPUT_INDEX = 1;
    private static final int EXPECTED_VALUE_INDEX = 3;
    private static final int SUM_INDEX = 2;
    private static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {

        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(twoNumbersSumComplement((int[]) input[INPUT_INDEX], (int) input[SUM_INDEX])),
                    Arrays.toString((int[]) input[EXPECTED_VALUE_INDEX]));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests ={
                { "Test 1", new int[] { 3, 5, -4, 8, 11, 1, -1, 6 }, 10, new int[] {-1, 11} },
                { "Test 2", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 17, new int[] {8, 9} },
                { "Test 3", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 15 }, 18, new int[] {3, 15} },
                { "Test 4", new int[] { -7, -5, -3, -1, 0, 1, 3, 5, 7 }, -5, new int[] {-5, 0} }
        };
        return tests;
    }

    /**
     * Since stream is lazy loading, time complexity for filter is O(n) in the worst case
     * We can use Map instead of /int[] complement/
     * Search operation in Map is 0(1) in most of the cases
     * Space complexity O(n)
     */
    private static int[] twoNumbersSumComplement(int[] array, int sum) {

        // HashMap<Integer, Integer> complement = new HashMap<>();
        int[] complement = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            int complementInt = sum - current;
            // if (complement.containsKey(current)) {
            if (IntStream.of(complement).anyMatch(x -> x == current)) {
                return (current > complementInt) ?
                        new int[] { complementInt, current } :
                        new int[] { current, complementInt };
            }
            // complement.put(complementInt, complementInt)
            complement[i] = complementInt;
        }

        return new int[] {};
    }

    /**
     * Since Arrays.sort TC is O(n log (n))
     * Space complexity is O(1)
     */
    private static int[] twoNumbersSumSorting(int[] array, int sum) {

        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int sumBothSides = array[left] + array[right];
            if (sumBothSides > sum) right--;
            else if (sumBothSides < sum) left++;
            else return new int[] { array[left], array[right] };
        }

        return new int[] {};
    }

}
