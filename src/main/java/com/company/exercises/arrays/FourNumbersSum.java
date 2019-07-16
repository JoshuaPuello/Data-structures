package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write a function that takes in a non-empty array of distinct integers and an integer
 * representing a target sum. The function should find all quadruplets in the array that
 * sum up to the target sum and return a two-dimensional array of all these quadruplets
 * in no particular order. If no four numbers sum up to the target sum, the function
 * should return an empty array.
 *
 * Sample input: [7, 6, 4, -1, 1, 2], 16
 * Sample output: [[7, 6, 4, -1], [7, 6, 1, 2]]
 */
public class FourNumbersSum {

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[0]),
                    fourNumbersSum((int[]) input[1], (int) input[2]),
                    (List<Integer[]>) ((Stream) input[3]).collect(Collectors.toList()));
        }

    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1, 2, 3, 4, 5, 6, 7 }, 10, Stream.of(
                        new Integer[][] { { 1, 2, 3, 4 } }
                ) },
                { "Test 2", new int[] { 7, 6, 4, -1, 1, 2 }, 16, Stream.of(
                        new Integer[][] { { 7, 6, 4, -1 }, { 7, 6, 1, 2 } }
                ) },
                { "Test 3", new int[] { 5, -5, -2, 2, 3, -3 }, 0, Stream.of(
                        new Integer[][] { { 5, -5, -2, 2 }, { 5, -5, 3, -3 },
                                { -2, 2, 3, -3 } }
                ) },
                { "Test 4", new int[] { -2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 4, Stream.of(
                        new Integer[][] { { -2, -1, 1, 6 }, { -2, 1, 2, 3 },
                                { -2, -1, 2, 5 }, { -2, -1, 3, 4 } }
                ) },
                { "Test 5", new int[] { -1, 22, 18, 4, 7, 11, 2, -5, -3 }, 30, Stream.of(
                        new Integer[][] { { -1, 22, 7, 2 }, { 22, 4, 7, -3 },
                                { -1, 18, 11, 2 }, { 18, 4, 11, -3 },
                                { 22, 11, 2, -5 } }
                ) },
                { "Test 6", new int[] { -10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5 }, 20, Stream.of(
                        new Integer[][] { { -5, 2, 15, 8 }, { -3, 2, -7, 28 },
                                { -10, -3, 28, 5 }, { -10, 28, -6, 8 },
                                { -7, 28, -6, 5 }, { -5, 2, 12, 11 },
                                { -5, 12, 8, 5 } }
                ) },
                { "Test 7", new int[] { 1, 2, 3, 4, 5 }, 100, Stream.of(
                        new Integer[][] { {  } }
                ) },
        };
        return tests;
    }

    /**
     * Time complexity O(n^2) | Worst case O(n^3)
     * Space complexity O(n^2)
     */
    private static ArrayList<Integer[]> fourNumbersSum(int[] array, int targetSum) {

        HashMap<Integer, ArrayList<Integer[]>> allPairsSum = new HashMap<>();
        ArrayList<Integer[]> quadruplets = new ArrayList<>();

        for (int i = 1; i < array.length - 1; i++) {

            for (int j = i + 1; j < array.length; j++) {

                int currentSum = array[i] + array[j];
                int difference = targetSum - currentSum;

                if (allPairsSum.containsKey(difference)) {
                    ArrayList<Integer[]> currentPairs = allPairsSum.get(difference);
                    for (Integer[] currentPair : currentPairs) {
                        quadruplets.add(new Integer[] { currentPair[0], currentPair[1], array[i], array[j] });
                    }
                }
            }

            for (int k = 0; k < i; k++) {

                int currentSum = array[i] + array[k];

                if (!allPairsSum.containsKey(currentSum)) {
                    ArrayList<Integer[]> pairs = new ArrayList<>();
                    pairs.add(new Integer[] { array[k], array[i] });
                    allPairsSum.put(currentSum, pairs);
                } else {
                    allPairsSum.get(currentSum).add(new Integer[] { array[k], array[i] });
                }

            }

        }

        return quadruplets;
    }
}
