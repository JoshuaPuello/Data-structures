package com.company.exercises.arrays;

import com.company.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Write a function that takes in an array of integers and returns an array of length 2 representing
 * the largest range of numbers contained in that array. The first number in the output array should
 * be the first number in the range while the second number should be the last number in the range.
 * A range of numbers is defined as a set of numbers that come right after each other in the set of
 * real integers. For instance, the output array [2, 6] represents the range {2, 3, 4, 5, 6}, which
 * is a range of length 5. Note that numbers do not need to be ordered or adjacent in the array in
 * order to form a range. Assume that there will only be one largest range.
 *
 * Sample input: [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
 * Sample output: [0, 7]
 *
 */
public class LargestRange {

    public static final int INPUT_INDEX = 1;
    public static final int EXPECTED_VALUE_INDEX = 2;
    public static final int TEST_NAME_INDEX = 0;

    public static void main(String[] args) {
        for (Object[] input : getInputs()) {
            Utils.printAssertEquals(String.valueOf(input[TEST_NAME_INDEX]),
                    Arrays.toString(largestRange((int[]) (input[INPUT_INDEX]))),
                    Arrays.toString((int[])(input[EXPECTED_VALUE_INDEX])));
        }
    }

    private static Object[][] getInputs() {
        Object[][] tests = {
                { "Test 1", new int[] { 1 }, new int[] { 1, 1 } },
                { "Test 2", new int[] { 1, 2 }, new int[] { 1, 2 } },
                { "Test 3", new int[] { 4, 2, 1, 3 }, new int[] { 1, 4 } },
                { "Test 4", new int[] { 4, 2, 1, 3, 6 }, new int[] { 1, 4 } },
                { "Test 5", new int[] { 8, 4, 2, 10, 3, 6, 7, 9, 1 }, new int[] { 6, 10 } },
        };
        return tests;
    }

    /**
     * Time complexity O(n)
     * Space complexity 0(n)
     */
    private static int[] largestRange(int[] array) {

        int[] bestRange = new int[2];
        int longestLength = 0;

        HashMap<Integer, Boolean> nums = new HashMap<>();

        for (int num: array) {
            nums.put(num, true);
        }

        for (int num: array) {

            if (!nums.containsKey(num)) {
                continue;
            }

            nums.put(num, false);
            int currentLength = 1;
            int left = num - 1;
            int right = num + 1;

            while (nums.containsKey(left)) {
                nums.put(left, false);
                currentLength++;
                left--;
            }

            while (nums.containsKey(right)) {
                nums.put(right, false);
                currentLength++;
                right++;
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                bestRange = new int[] {left + 1, right - 1};
            }
        }

        return bestRange;
    }

}
